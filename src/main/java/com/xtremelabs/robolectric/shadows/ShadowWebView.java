package com.xtremelabs.robolectric.shadows;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.internal.Implementation;
import com.xtremelabs.robolectric.internal.Implements;

import java.util.HashMap;

@SuppressWarnings({"UnusedDeclaration"})
@Implements(WebView.class)
public class ShadowWebView extends ShadowAbsoluteLayout {

    private String lastUrl;
    private String lastLoadedData;
    private String lastLoadedMimeType;
    private String lastLoadedEncoding;
    private HashMap<String, Object> javascriptInterfaces = new HashMap<String, Object>();
    private WebSettings webSettings = Robolectric.newInstanceOf(WebSettings.class);
    private WebViewClient webViewClient = null;
    private boolean runFlag = false;
    private boolean clearCacheCalled = false;
    private boolean clearCacheIncludeDiskFiles = false;
    private boolean clearFormDataCalled = false;
    private boolean clearHistoryCalled = false;
    private boolean clearViewCalled = false;
    private boolean destroyCalled = false;
    private WebChromeClient webChromeClient;
    private boolean canGoBack;
    private int goBackInvocations = 0;

    @Override
    public void __constructor__(Context context, AttributeSet attributeSet) {
        super.__constructor__(context, attributeSet);
    }

    @Implementation
    public void loadUrl(String url) {
        lastUrl = url;
    }

    @Implementation
    public void loadData(String data, String mimeType, String encoding) {
        lastLoadedData = data;
        lastLoadedMimeType = mimeType;
        lastLoadedEncoding = encoding;
    }

    /**
     * Non-Android accessor.
     *
     * @return the last loaded url
     */
    public String getLastLoadedUrl() {
        return lastUrl;
    }

    @Implementation
    public WebSettings getSettings() {
        return webSettings;
    }

    @Implementation
    public void setWebViewClient(WebViewClient client) {
        webViewClient = client;
    }

    @Implementation
    public void setWebChromeClient(WebChromeClient client) {
        webChromeClient = client;
    }

    public WebViewClient getWebViewClient() {
        return webViewClient;
    }

    @Implementation
    public void addJavascriptInterface(Object obj, String interfaceName) {
        javascriptInterfaces.put(interfaceName, obj);
    }

    public Object getJavascriptInterface(String interfaceName) {
        return javascriptInterfaces.get(interfaceName);
    }

    @Implementation
    public void clearCache(boolean includeDiskFiles) {
        clearCacheCalled = true;
        clearCacheIncludeDiskFiles = includeDiskFiles;
    }

    public boolean wasClearCacheCalled() {
        return clearCacheCalled;
    }

    public boolean didClearCacheIncludeDiskFiles() {
        return clearCacheIncludeDiskFiles;
    }

    @Implementation
    public void clearFormData() {
        clearFormDataCalled = true;
    }

    public boolean wasClearFormDataCalled() {
        return clearFormDataCalled;
    }

    @Implementation
    public void clearHistory() {
        clearHistoryCalled = true;
    }

    public boolean wasClearHistoryCalled() {
        return clearHistoryCalled;
    }

    @Implementation
    public void clearView() {
        clearViewCalled = true;
    }

    public boolean wasClearViewCalled() {
        return clearViewCalled;
    }

    @Implementation
    public void destroy() {
        destroyCalled = true;
    }

    public boolean wasDestroyCalled() {
        return destroyCalled;
    }

    @Implementation
    public void post(Runnable action) {
        action.run();
        runFlag = true;
    }

    public boolean getRunFlag() {
        return runFlag;
    }


    /**
     * Non-Android accessor.
     *
     * @return webChromeClient
     */
    public WebChromeClient getWebChromeClient() {
        return webChromeClient;
    }

    @Implementation
    public boolean canGoBack() {
        return canGoBack;
    }

    @Implementation
    public void goBack() {
        goBackInvocations++;
    }


    /**
     * Non-Android accessor.
     *
     * @return goBackInvocations the number of times {@code android.webkit.WebView#goBack()}
     * was invoked
     */
    public int getGoBackInvocations() {
        return goBackInvocations;
    }

    /**
     * Non-Android setter.
     *
     * Sets the value to return from {@code android.webkit.WebView#canGoBack()}
     *
     * @param canGoBack
     */
    public void setCanGoBack(boolean canGoBack) {
        this.canGoBack = canGoBack;
    }

    public String getLastLoadedData() {
        return lastLoadedData;
    }

    public String getLastLoadedMimeType() {
        return lastLoadedMimeType;
    }

    public String getLastLoadedEncoding() {
        return lastLoadedEncoding;
    }
}
