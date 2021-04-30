package ui;

import euromillions.CuponEuromillions;
import euromillions.Dip;
import euromillions.EuromillionsDraw;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DemoMain {

    private static Logger logger = Logger.getAnonymousLogger();
    /**
     * demonstrates a client for ramdom euromillions bets
     */
    public static void main(String[] args) {

        // played sheet
        CuponEuromillions thisWeek = new CuponEuromillions();

        logger.log(Level.ALL,"Betting with three random bets...");
        thisWeek.addDipToCuppon(Dip.generateRandomDip());
        thisWeek.addDipToCuppon(Dip.generateRandomDip());
        thisWeek.addDipToCuppon(Dip.generateRandomDip());

        // simulate a random draw
        EuromillionsDraw draw = EuromillionsDraw.generateRandomDraw();

        //report results
        logger.log(Level.ALL,"You played:");
        String thisWeekFormat = thisWeek.format();
        logger.log(Level.ALL,thisWeekFormat);

        logger.log(Level.ALL,"Draw results:");
        String drawResultsFormat = draw.getDrawResults().format();
        logger.log(Level.ALL,drawResultsFormat);

        logger.log(Level.ALL,"Your score:");
        for (Dip dip : draw.findMatches(thisWeek)) {
            String dipFormat = dip.format();
            logger.log(Level.ALL,dipFormat);

        }
    }
}
