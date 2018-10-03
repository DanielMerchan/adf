package com.magicpigeon.i18n.am;

import com.magicpigeon.i18n.am.common.I18AppModule;

import java.util.Arrays;
import java.util.HashMap;

import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewObjectImpl;

import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Aug 07 10:08:25 BST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
/**
 * Client Interface for the I18N Application Module
 * @author magicpigeon
 * @version 1.0
 */
public class I18AppModuleImpl extends ApplicationModuleImpl implements I18AppModule {
    
    /**
     * Logging
     */
    private static final ADFLogger LOG = ADFLogger.createADFLogger(I18AppModuleImpl.class);
    
    /**
     * Class Name
     */
    private static final String CLASS_NAME = ApplicationModuleImpl.class.getName();
    
    /**
     * This is the default constructor (do not remove).
     */
    public I18AppModuleImpl() {
    }
    
    // Custom Client Interface Methods
    
    /**
     * Retrieve all the labels for a specified Locale
     * @param localeStr - String locale (E.g.: es_ES or en_US)
     * @return Map - Contains all the texts associated to a specific Locale
     */
    public Map<String,String> getResourceBundle(final String localeStr) {
        final String METHOD_NAME = Thread.currentThread().getStackTrace()[2].getMethodName();
        LOG.entering(CLASS_NAME, METHOD_NAME);
        LOG.info(CLASS_NAME,METHOD_NAME, "Retrieving the text for the locale: " + localeStr);
        Map<String,String> result = new HashMap<String,String>();
        // Call to Database for retrieving the text
        ViewObject boilerplateText = getI18nResourceBundleValuesRO();
        boilerplateText.setNamedWhereClauseParam("localeVar", localeStr);   
        boilerplateText.executeQuery();
        RowSetIterator boilerplateTextRowSetIterator = boilerplateText.createRowSetIterator(null);
        while (boilerplateTextRowSetIterator.hasNext()) {
            Row text = boilerplateTextRowSetIterator.next();
            result.put((String)text.getAttribute("ResourceBundleKey"),(String)text.getAttribute("ResourceBundleValue"));
        }
        if (LOG.isFine()) {
            LOG.fine(CLASS_NAME, METHOD_NAME, Arrays.toString(result.entrySet().toArray()));
        }
        LOG.exiting(CLASS_NAME, METHOD_NAME);
        boilerplateTextRowSetIterator.closeRowSetIterator();
        return result;
    }                                                                 

    /**
     * Container's getter for I18nResourceBundleValuesView1.
     * @return I18nResourceBundleValuesView1
     */
    public ViewObjectImpl getI18nResourceBundleValues() {
        return (ViewObjectImpl) findViewObject("I18nResourceBundleValues");
    }

    /**
     * Container's getter for I18nResourceBundleValuesView2.
     * @return I18nResourceBundleValuesView2
     */
    public ViewObjectImpl getI18nResourceBundleValuesRO() {
        return (ViewObjectImpl) findViewObject("I18nResourceBundleValuesRO");
    }
}

