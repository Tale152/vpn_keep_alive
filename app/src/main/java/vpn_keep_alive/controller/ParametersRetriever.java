package vpn_keep_alive.controller;

import vpn_keep_alive.model.Parameters;

public interface ParametersRetriever {

    Parameters retrieveParameters() throws IllegalArgumentException;

}
