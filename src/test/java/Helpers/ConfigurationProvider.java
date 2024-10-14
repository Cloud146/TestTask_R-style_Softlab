package Helpers;

import java.io.IOException;

public class ConfigurationProvider {

    public String getMainPageURL() throws IOException {
        return ConfigurationManager.getInstance().getProperty("main.page.url");
    }

    public String getImportSubstitutionPageURL() throws IOException {
        return ConfigurationManager.getInstance().getProperty("importSubstitution.page.url");
    }

    public String getRSBankPageURL() throws IOException {
        return ConfigurationManager.getInstance().getProperty("rsBank.page.url");
    }

    public String getRSBankV6PageURL() throws IOException {
        return ConfigurationManager.getInstance().getProperty("rsBankV6.page.url");
    }

    public String getTechnicalDocumentationPDFURL() throws IOException {
        return ConfigurationManager.getInstance().getProperty("technicalDocumentation.pdf.url");
    }

    public Integer getScreenWidth() throws IOException {
        return Integer.parseInt(ConfigurationManager.getInstance().getProperty("screen.width"));
    }

    public Integer getScreenHeight() throws IOException {
        return Integer.parseInt(ConfigurationManager.getInstance().getProperty("screen.height"));
    }

}
