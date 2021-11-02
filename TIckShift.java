// these are the names of the packages for my client (spidersense) but they will change depending on what client ur skidding this into
package com.gamesense.client.module.modules.exploits;

import com.gamesense.api.setting.values.DoubleSetting;
import com.gamesense.api.setting.values.IntegerSetting;
import com.gamesense.client.module.Category;
import com.gamesense.client.module.Module;

/*
* @author hausemasterissue
* @since 1/11/2021
*/

// this declares the module, usually you will do a constructer method with a super keyword
@Module.Declaration(name = "TickShift", category = Category.Exploits)
public class TickShift extends Module {
	
    // the amount of ticks to wait until the module auto-disables
    IntegerSetting disableTicks = registerInteger("DisableTicks", 17, 1, 100);
    // the game tick speed multiplier
    DoubleSetting multiplier = registerDouble("Multiplier", 3.0, 1.0, 10.0);
	
    private int ticksPassed = 0;
	
    // the method that gets called when the module is enabled
    public void onEnable() {
        // multiply the tickspeed of the client by the multiplier
	      mc.timer.tickLength = ((float)(50.0 / multiplier.getValue()));
    }
	
    // the method that gets called once every game update (1 tick)
    public void onUpdate() {
        // everytime a tick passes update the amount of ticks passed
	      ticksPassed++;
	    
        // if the amount of ticks passed is greater than or equal to the amount of ticks that need to pass for the module to disable, disable the module (duh)
	      if(ticksPassed >= disableTicks.getValue()) {
		        ticksPassed = 0;
            // disable the module
		        disable();	
	      }
    }
	  
    // the method that gets called when the module is disabled
    public void onDisable() {
        // set the client tick length back to the default
	      mc.timer.tickLength = 50f;    
    }
  


}
