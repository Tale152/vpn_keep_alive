package vpn_keep_alive;

import vpn_keep_alive.controller.ApplicationController;
import vpn_keep_alive.controller.ParametersRetriever;
import vpn_keep_alive.controller.impl.ExecutorServiceApplicationController;
import vpn_keep_alive.controller.impl.cmd.OnRequestErrorStrategyCmd;
import vpn_keep_alive.controller.impl.cmd.OnRequestResponseStrategyCmd;
import vpn_keep_alive.controller.impl.cmd.ParametersRetrieverCmd;
import vpn_keep_alive.utils.Logger;

public class AppCmd {

    public void execute(final String[] args){
        ParametersRetriever parametersRetriever = ParametersRetrieverCmd.builder().args(args).build();
        ApplicationController controller = ExecutorServiceApplicationController.builder()
                .parametersRetriever(parametersRetriever)
                .build();
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> controller.stop(() -> Logger.printLogOnConsole("Closing application... bye :D")))
        );
        controller.start(
                new OnRequestResponseStrategyCmd(),
                new OnRequestErrorStrategyCmd()
        );
    }

}
