package com.bitdubai.fermat_core;

import com.bitdubai.fermat_api.Plugin;
import com.bitdubai.fermat_api.layer.all_definition.common.exceptions.CantGetModuleManagerException;
import com.bitdubai.fermat_api.layer.all_definition.common.exceptions.CantRegisterPlatformException;
import com.bitdubai.fermat_api.layer.all_definition.common.exceptions.CantStartSystemException;
import com.bitdubai.fermat_api.layer.all_definition.common.exceptions.ModuleManagerNotFoundException;
import com.bitdubai.fermat_api.layer.all_definition.common.exceptions.VersionNotFoundException;
import com.bitdubai.fermat_api.layer.all_definition.common.utils.VersionReference;
import com.bitdubai.fermat_api.layer.modules.ModuleManager;
import com.bitdubai.fermat_ccp_core.CCPPlatform;

/**
 * The class <code>com.bitdubai.fermat_core.FermatSystem</code>
 * starts all the component of the platform and manage it.
 * <p/>
 * Created by Leon Acosta - (laion.cj91@gmail.com) on 22/10/2015.
 */
public class FermatSystem {

    private final FermatSystemContext fermatSystemContext;

    public FermatSystem() {
        fermatSystemContext = new FermatSystemContext();
    }

    /**
     * Here we start all the platforms of Fermat, one by one.
     *
     * @throws CantStartSystemException if something goes wrong.
     */
    public final void start() throws CantStartSystemException {

        try {

            fermatSystemContext.registerPlatform(new CCPPlatform());

        } catch(CantRegisterPlatformException e) {

            throw new CantStartSystemException(e, "", "There was a problem registering a Platform.");
        } catch(Exception e) {

            throw new CantStartSystemException(e, "", "Unhandled Exception.");
        }

    }

    /**
     * Throw the method <code>getModuleManager</code> the graphic interface can access to the modules of
     * its sub-apps and wallets.
     *
     * @param versionReference plugin version reference data.
     *
     * @return an instance of the requested module manager.
     *
     * @throws CantGetModuleManagerException   if something goes wrong.
     * @throws ModuleManagerNotFoundException  if we can't find the requested module manager.
     */
    public final ModuleManager getModuleManager(final VersionReference versionReference) throws CantGetModuleManagerException  ,
                                                                                                ModuleManagerNotFoundException {

        try {

            Plugin moduleManager = fermatSystemContext.getPluginVersion(versionReference);

            if (moduleManager instanceof ModuleManager)
                return (ModuleManager) moduleManager;
            else
                throw new CantGetModuleManagerException(versionReference.toString(), "The plugin version requested not implements module manager interface.");

        } catch(VersionNotFoundException e) {

            throw new ModuleManagerNotFoundException(e, versionReference.toString(), "The module manager cannot be found.");
        } catch (Exception e) {

            throw new CantGetModuleManagerException(e, versionReference.toString(), "Unhandled error.");
        }
    }

}
