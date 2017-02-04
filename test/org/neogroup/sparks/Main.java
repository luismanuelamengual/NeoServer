
package org.neogroup.sparks;

import org.neogroup.sparks.processors.TestProcessor;

public class Main {

    public static void main(String[] args) {

        WebApplication application = new WebApplication();
        application.registerProcessor(TestProcessor.class);
        application.start();
    }
}