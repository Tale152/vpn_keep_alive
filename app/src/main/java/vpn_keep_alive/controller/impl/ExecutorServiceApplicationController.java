package vpn_keep_alive.controller.impl;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import vpn_keep_alive.controller.ApplicationController;
import vpn_keep_alive.controller.OnRequestErrorStrategy;
import vpn_keep_alive.controller.OnRequestResponseStrategy;
import vpn_keep_alive.controller.OnStopStrategy;
import vpn_keep_alive.controller.ParametersRetriever;
import vpn_keep_alive.model.Parameters;
import vpn_keep_alive.model.RequestResponse;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExecutorServiceApplicationController implements ApplicationController {

    private final static Long INITIAL_DELAY = 0L;

    private final ParametersRetriever parametersRetriever;
    private final ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void start(
            final OnRequestResponseStrategy onRequestResponseStrategy,
            final OnRequestErrorStrategy onRequestErrorStrategy
            ) throws IllegalArgumentException {
        Parameters parameters = parametersRetriever.retrieveParameters();
        scheduledExecutor.scheduleAtFixedRate(
                new HttpRequestRunnable(parameters, onRequestResponseStrategy, onRequestErrorStrategy),
                INITIAL_DELAY,
                parameters.getInterval(),
                TimeUnit.MILLISECONDS
        );
    }

    @Override
    public void stop(final OnStopStrategy onStopStrategy) {
        this.scheduledExecutor.shutdownNow();
        onStopStrategy.performStrategy();
    }

    @AllArgsConstructor
    private static class HttpRequestRunnable implements Runnable{

        private final Parameters parameters;
        private final OnRequestResponseStrategy onRequestResponseStrategy;
        private final OnRequestErrorStrategy onRequestErrorStrategy;

        private final OkHttpClient client = new OkHttpClient();

        @Override
        public void run() {
            Request request = new Request.Builder().url(parameters.getUrl()).build();
            try {
                Response response = client.newCall(request).execute();
                onRequestResponseStrategy.performStrategy(RequestResponse.builder().code(response.code()).build());
            } catch (final IOException e) {
                onRequestErrorStrategy.performStrategy(e);
            }
        }
    }
}
