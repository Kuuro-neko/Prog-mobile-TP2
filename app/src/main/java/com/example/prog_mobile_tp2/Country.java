package com.example.prog_mobile_tp2;

import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class Country {
    private String name;
    private String capital;
    private int area;
    private int population;
    private String currency;
    private String language;
    private String flag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    public static Country getCountryByName(XmlResourceParser parser, String countryName) {
        try {
            int eventType = parser.getEventType();
            Country country = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("country".equals(tagName)) {
                            country = new Country();
                        } else if (country != null) {
                            switch (tagName) {
                                case "name":
                                    country.setName(parser.nextText());
                                    break;
                                case "capital":
                                    country.setCapital(parser.nextText());
                                    break;
                                case "area":
                                    country.setArea(Integer.parseInt(parser.nextText()));
                                    break;
                                case "population":
                                    country.setPopulation(Integer.parseInt(parser.nextText()));
                                    break;
                                case "currency":
                                    country.setCurrency(parser.nextText());
                                    break;
                                case "language":
                                    country.setLanguage(parser.nextText());
                                    break;
                                case "flag":
                                    country.setFlag(parser.nextText());
                                    break;
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("country".equals(tagName) && country != null && country.getName().equals(countryName)) {
                            return country;
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Country> readData(XmlResourceParser parser) {
        ArrayList<Country> countries = new ArrayList<>();
        try {
            int eventType = parser.getEventType();
            Country country = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("country".equals(tagName)) {
                            country = new Country();
                        } else if (country != null) {
                            switch (tagName) {
                                case "name":
                                    country.setName(parser.nextText());
                                    break;
                                case "capital":
                                    country.setCapital(parser.nextText());
                                    break;
                                case "area":
                                    country.setArea(Integer.parseInt(parser.nextText()));
                                    break;
                                case "population":
                                    country.setPopulation(Integer.parseInt(parser.nextText()));
                                    break;
                                case "currency":
                                    country.setCurrency(parser.nextText());
                                    break;
                                case "language":
                                    country.setLanguage(parser.nextText());
                                    break;
                                case "flag":
                                    country.setFlag(parser.nextText());
                                    break;
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("country".equals(tagName) && country != null) {
                            countries.add(country);
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        for (Country country : countries) {
            System.out.println(country.getName());
        }
        return countries;
    }

}
