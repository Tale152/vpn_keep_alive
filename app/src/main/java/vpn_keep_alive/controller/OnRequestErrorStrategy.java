package vpn_keep_alive.controller;

public interface OnRequestErrorStrategy {

    void performStrategy(final Exception exception);

}
