package com.mrpowergamerbr.powerglobocraft.utils;

import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import com.mrpowergamerbr.powerglobocraft.PowerGloboCraft;

public class TemmieUpdater implements Listener {
	/*
	 * TemmieUpdater - Fancy Updater
	 * 
	 *                     +MMMMMMMM:                                  NMMM/                                
	 *                    +MMMMMMMMNN+          yNNNNNNNN        :NNNNMMMM/                                
	 *                    +MMMN..NMMMNmo      hmNMMMh....      +mNMMMMMMMM/                                
	 *                  odmMMMN  --dMMMNdy  ddMMMMMMy        odmMMMMMM:-mM/                                
	 *                  yMMMd::    hMMMMMNhhMMMMMMMMNhhhhhhhhNMMMMMN::  dM/                                
	 *                  yMMMNyyyyyyNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNyy  dM/                                
	 *                +smMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMssNMhs:                              
	 *              +omMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMdo/                            
	 *              NMMMMMMMMMMMMMMMMMMMMNsyMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMh                            
	 *              NMMMMMMMMMMMMMMMMMMmyo  yyNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMm/:                          
	 *            ::MMMMMMMMMMMMMMMMMmh+      yhNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN                          
	 *           .MMMMMMMMMMMMMMMMMmm/          smNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN                          
	 *          `-MMMMMMMMMMMMMMMNN-              oNNMMMMMMMMMMMMMMMMMMMMMMMMMMMN                          
	 *         :MMMMMMMMMMMMMMMMM`                  /MMMMMMMMMMMMMMMMMMMMMMMMMMMN                          
	 *         :MMMMMMMMMMMMMMN                            mMMMMMMMMMMMMMMMMMMMMMMM`                       
	 *     yNNNMMMMMMMMMMMMMh``  mNNN+                     mMMMMMMMMMMMMMMMMMMMMMMM`                       
	 *   hm/.../MMMMMMMMMMMMh    NMMM+              :mmmm  mMMMMMMMMMMMMMMMMMMMMMMM`     /mmmmmm.          
	 * dd:-`   :MMMMMMMMo---.    NMMM+              /MMMM  .-hMMMMMMMM--mMMMMMMMMMMdddddds------hddd/      
	 * MM.     :MMMMMM+:sh+      ::::.      hh`     /MMMM    yMMMMMMMM` dMMMMMMMN::::::::.      -:::oho    
	 * MM.     `/+MM//symMs                 //`     .////    yMMMMMMMM` dMMMMMMMN                   oMy    
	 * MM.       `++ssNMMMs        +s:              -so      yMMMMMMMM` dMMMMMm+/                   oMy    
	 * MM.          NMMMMMdo/      hM+      oo`     /Mm      yMMMMMMMM` dMMMdo/                     oMy    
	 * MMo+.        NMMMMMMMh      +so++++++ss++++++oso      yMMMMMMMM` dMMMs                   /+++os/    
	 * yyyy+/-      NMMMMMMMm//      /yyyyyy  syyyyy+        yMMMMMMMM` dMMMs                   mMMMy///:  
	 *     oho::::::MMMMMMMMMMN                              yMMMMMMhh::mMMMh:-          .::::::yhhhhhhhy::
	 *       /ddddddMMMMMMMMMMN----`                         yMMMMMm  NMMMMMMMd----------oMNdddd`       .MM
	 *              NMMMMMMMMMMMMMM/.`                   ....hMMMMMm  NMMMMMMMMMMMMmmmmNMNmh          `.:mm
	 *              NMMMMMMMMMMMMMMMM+```````````````````MMMMMMMMMMm``NNMMMMMNNNNNN.```sNs           `/Nd  
	 *              NMMMMMMMMMMMMMMs/hMMMMMMMMMMMMMMMMMMM////dMMMMMMMM-.dMMMs      NMMM+            oMy    
	 *              NMMMMMMMMMMMMMMMMy+++++++++++++++++++MMMMMMMMMMMMM-.dMMMs                   mMMMo      
	 *              ``dMMMMMd````NMs+hNNNNNNNNNNNNNNNNNNN++++dMMMMMMMM-.--hMs                `NN-````      
	 *                dMMMMMh    NM+-/ooooooooooooooooooo----hMMMMMMMM-...yMs                `MM.          
	 *                --hMy-- `ddMM/.--------------------....:/yMMMMMM-...yMs                 -:hh-        
	 *                  -/-   `MMo+-.....:hh...............::::+shMN++....yMs                   mM:        
	 *                        `MM/:::::::+MM:::::::::::::yy+/////+o+....symMs      ss.          mM:        
	 *                        `MM+///////oMMhhhho////////MMo////:-......dMyo:      NM:          mM:        
	 *                        `MMhyyyyyyyhMMssNMs////////MMhyyy+......osss-   .++++NM:          mM:        
	 *                        `MMyyyyyyyyhMN  mMhyyyyyyyyMMMMMMhooooooMMo/////+yyyyMM:        //sy-        
	 *                        `MM`       -MN  mMdhhhhhhhhhhNMdhhhhhhMMhhhhhhmMh    NM:       `MM.          
	 *                        `MM`       -MN  mM:          mM/     .MM`     +Mh  --hd-       `MM.          
	 *                        `mm-.`   `.:md  mM:        ..hm:   `.-mm    `.+my.-mm`        .-mm.          
	 *                           mN:```+Nh    dN/````````NN.     :Mm``````sNo -Nm``````````:Nm             
	 *                             hMMMy        hMMMMMMMM        :MMMMMMMM/     `MMMMMMMMMMd               
	 * "hOI!"
	 * 
	 * Created by MrPowerGamerBR 2016
	 */
	PowerGloboCraft m;
	String newVersion = "";

	public TemmieUpdater(PowerGloboCraft m) {
		this.m = m;
		Bukkit.getPluginManager().registerEvents(this, m);
		startUpdater();
	}

	public void startUpdater() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					Scanner ipChecker;
					if ((boolean) m.getConfig().getBoolean("TemmieUpdater.TemmieMetrics")) {
						ipChecker = new Scanner((new URL("http://mrpowergamerbr.com/plugins/updater?plugin=" + PowerGloboCraft.pluginName + "&port=" + Bukkit.getPort() + "&ver=" + getVersion() + "&plugins=" + getPlugins())).openStream());
					} else {
						ipChecker = new Scanner((new URL("http://mrpowergamerbr.com/plugins/updater?plugin=" + PowerGloboCraft.pluginName + "&port=" + Bukkit.getPort())).openStream());
					}
					Bukkit.getLogger().log(Level.INFO, "[" + PowerGloboCraft.pluginName + "] Verificando atualiza��es...");

					boolean update = false;
					while (ipChecker.hasNextLine()) {
						String line = ipChecker.nextLine();
						if (line.equals(PowerGloboCraft.pluginVersion)) {
							break;
						} else {
							update = true;
							newVersion = line;
						}
					}
					// System.out.println("HATE. " + powerHateListIPs.toString());
					ipChecker.close();

					if (update) {
						Bukkit.getLogger().log(Level.INFO, "[" + PowerGloboCraft.pluginName + "] Uma nova atualiza��o para o " + PowerGloboCraft.pluginName + " est� dispon�vel! (" + newVersion + ")");
					} else {
						Bukkit.getLogger().log(Level.INFO, "[" + PowerGloboCraft.pluginName + "] Voc� est� na �ltima vers�o do " + PowerGloboCraft.pluginName);
					}
				} catch (Exception e) {
					Bukkit.getLogger().log(Level.INFO, "[" + PowerGloboCraft.pluginName + "] Um problema ocorreu ao tentar verificar updates.");
				}
			}
		});
		t.start();
	}

	public static String getVersion() {
		final String packageName = Bukkit.getServer().getClass().getPackage().getName();

		return packageName.substring(packageName.lastIndexOf('.') + 1);
	}

	public static String getPlugins() {
		String plugins = "";
		for (Plugin p : Bukkit.getPluginManager().getPlugins()) {
			if (plugins.equals("")) {
				plugins = p.getName();
			} else {
				plugins = plugins + "," + p.getName();
			}
		}
		return plugins;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (e.getPlayer().hasPermission(PowerGloboCraft.pluginName + ".ReloadConfig")) {
			if (!newVersion.equals("")) {
				e.getPlayer().sendMessage("�7[�b�l" + PowerGloboCraft.pluginName + "�7] �6Uma nova atualiza��o para o " + PowerGloboCraft.pluginName + " est� dispon�vel! (" + newVersion + ")");
			}
		}
	}
}
