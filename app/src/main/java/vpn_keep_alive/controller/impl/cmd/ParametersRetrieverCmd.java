package vpn_keep_alive.controller.impl.cmd;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import vpn_keep_alive.controller.ParametersRetriever;
import vpn_keep_alive.model.Parameters;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ParametersRetrieverCmd implements ParametersRetriever {

    private final String[] args;

    @Override
    public Parameters retrieveParameters() throws IllegalArgumentException {
        if(args == null || args.length != 2){
            throw new IllegalArgumentException("Incorrect number of arguments");
        }
        String url = args[0].trim();
        try{
            long interval = Long.parseLong(args[1]);
            if(interval < 0L){
                throw new IllegalArgumentException("Interval cannot be less than zero");
            }
            return Parameters.builder().url(url).interval(interval).build();
        } catch (final NumberFormatException e){
            throw new IllegalArgumentException("Error while parsing interval");
        }
    }
}
