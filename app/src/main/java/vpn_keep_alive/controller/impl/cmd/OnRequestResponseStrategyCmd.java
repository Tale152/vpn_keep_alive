package vpn_keep_alive.controller.impl.cmd;

import vpn_keep_alive.controller.OnRequestResponseStrategy;
import vpn_keep_alive.model.RequestResponse;
import vpn_keep_alive.utils.Logger;

public class OnRequestResponseStrategyCmd implements OnRequestResponseStrategy {

    @Override
    public void performStrategy(final RequestResponse requestResponse) {
        if(requestResponse.getCode() == 200 || requestResponse.getCode() == 201){
            Logger.printLogOnConsole("OK");
        } else {
            Logger.printLogOnConsole("Something went wrong (" + requestResponse.getCode() + ")");
        }
    }

}
