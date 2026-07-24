package org.autumn.signal.core.utilities;

import java.util.Arrays;
import java.util.List;

/**
 * @author Chemthunder
 */
public interface ConsoleFeedback {
    List<ConsoleEntry> DEPLOY_DROPSHIP = Arrays.asList(
            new ConsoleEntry("Deployment Area located.", 10),
            new ConsoleEntry("Beacon established.", 10),
            new ConsoleEntry("Stand by for payload deployment.", 40)
    );

    List<ConsoleEntry> PAYLOAD_DEPLOYED = Arrays.asList(
            new ConsoleEntry("Payload secured, clear combat zone.", 30),
            new ConsoleEntry("Connection relay finished, await further instruction.", 80)
    );
}
