package vpn_keep_alive.controller.impl.cmd;

import vpn_keep_alive.controller.OnRequestErrorStrategy;
import vpn_keep_alive.utils.Logger;

public class OnRequestErrorStrategyCmd implements OnRequestErrorStrategy {
    @Override
    public void performStrategy(final Exception exception) {
        Logger.printLogOnConsole("Something went wrong (" + exception.getMessage() + ")");
    }

}
