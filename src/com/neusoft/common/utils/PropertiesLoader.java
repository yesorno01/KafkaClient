/**
 * application name:scrm-jstorm
 * application describing:this class handles the request of the client
 * copyright:Copyright(c) 2017 Neusoft LTD.
 * company:Neusoft
 * time:2017年8月14日 下午3:41:08
 *
 * @author:许辰
 * @version:[v1.0]
 */
package com.neusoft.common.utils;

import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesLoader {

    public volatile static PropertiesConfiguration pc = null;

    public static PropertiesConfiguration load() {
        if (null == pc) {
            synchronized (PropertiesLoader.class) {
                if (null == pc) {
                    try {
                        pc = new PropertiesConfiguration("config.properties");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return pc;
    }
}
