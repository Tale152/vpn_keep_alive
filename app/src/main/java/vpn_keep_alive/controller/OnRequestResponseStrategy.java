package vpn_keep_alive.controller;

import vpn_keep_alive.model.RequestResponse;

public interface OnRequestResponseStrategy {

    void performStrategy(final RequestResponse requestResponse);

}
