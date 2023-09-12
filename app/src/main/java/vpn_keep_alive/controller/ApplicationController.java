package vpn_keep_alive.controller;

public interface ApplicationController {

    void start(
            final OnRequestResponseStrategy onRequestResponseStrategy,
            final OnRequestErrorStrategy onRequestErrorStrategy
    ) throws IllegalArgumentException;

    void stop(final OnStopStrategy onStopStrategy);

}
