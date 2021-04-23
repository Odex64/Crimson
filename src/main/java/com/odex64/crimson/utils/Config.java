package com.odex64.crimson.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Config
{
    public static final String prefix = "-";
    public static final Logger LOGGER = LoggerFactory.getLogger(Config.class);
    public static final Random rand = new Random();
    public static String ownerId;
    public static String botId;
}
