package com.bitdubai.fermat_dap_plugin.layer.digital_asset_transaction.asset_reception.developer.bitdubai.version_1;

import com.bitdubai.fermat_api.CantStartPluginException;
import com.bitdubai.fermat_api.FermatException;
import com.bitdubai.fermat_api.Plugin;
import com.bitdubai.fermat_api.Service;
import com.bitdubai.fermat_api.layer.all_definition.developer.DatabaseManagerForDevelopers;
import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperDatabase;
import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperDatabaseTable;
import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperDatabaseTableRecord;
import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperObjectFactory;
import com.bitdubai.fermat_api.layer.all_definition.developer.LogManagerForDevelopers;
import com.bitdubai.fermat_api.layer.all_definition.enums.Plugins;
import com.bitdubai.fermat_api.layer.all_definition.enums.ServiceStatus;
import com.bitdubai.fermat_api.layer.all_definition.events.EventSource;
import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEvent;
import com.bitdubai.fermat_api.layer.dmp_world.wallet.exceptions.CantStartAgentException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.Database;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DealsWithPluginDatabaseSystem;
import com.bitdubai.fermat_api.layer.osa_android.database_system.PluginDatabaseSystem;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantCreateDatabaseException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantOpenDatabaseException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.DatabaseNotFoundException;
import com.bitdubai.fermat_api.layer.osa_android.file_system.DealsWithPluginFileSystem;
import com.bitdubai.fermat_api.layer.osa_android.file_system.PluginFileSystem;
import com.bitdubai.fermat_api.layer.osa_android.logger_system.DealsWithLogger;
import com.bitdubai.fermat_api.layer.osa_android.logger_system.LogLevel;
import com.bitdubai.fermat_api.layer.osa_android.logger_system.LogManager;
import com.bitdubai.fermat_bch_api.layer.crypto_network.bitcoin.interfaces.BitcoinNetworkManager;
import com.bitdubai.fermat_bch_api.layer.crypto_network.bitcoin.interfaces.DealsWithBitcoinNetwork;
import com.bitdubai.fermat_dap_api.layer.all_definition.contracts.exceptions.CantDefineContractPropertyException;
import com.bitdubai.fermat_dap_api.layer.all_definition.exceptions.CantSetObjectException;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_issuer.interfaces.ActorAssetIssuerManager;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_issuer.interfaces.DealsWithActorAssetIssuer;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.interfaces.ActorAssetUserManager;
import com.bitdubai.fermat_dap_api.layer.dap_actor.asset_user.interfaces.DealsWithActorAssetUser;
import com.bitdubai.fermat_dap_api.layer.dap_network_services.asset_transmission.interfaces.AssetTransmissionNetworkServiceManager;
import com.bitdubai.fermat_dap_api.layer.dap_network_services.asset_transmission.interfaces.DealsWithAssetTransmissionNetworkServiceManager;
import com.bitdubai.fermat_dap_api.layer.dap_transaction.asset_reception.interfaces.AssetReceptionManager;
import com.bitdubai.fermat_dap_api.layer.dap_transaction.common.exceptions.CantDeliverDatabaseException;
import com.bitdubai.fermat_dap_api.layer.dap_transaction.common.exceptions.CantExecuteDatabaseOperationException;
import com.bitdubai.fermat_dap_api.layer.dap_transaction.common.exceptions.CantPersistDigitalAssetException;
import com.bitdubai.fermat_dap_api.layer.dap_transaction.common.exceptions.CantStartServiceException;
import com.bitdubai.fermat_dap_api.layer.dap_wallet.asset_user_wallet.interfaces.AssetUserWalletManager;
import com.bitdubai.fermat_dap_api.layer.dap_wallet.asset_user_wallet.interfaces.DealsWithAssetUserWallet;
import com.bitdubai.fermat_dap_plugin.layer.digital_asset_transaction.asset_reception.developer.bitdubai.version_1.developer_utils.AssetReceptionDeveloperDatabaseFactory;
import com.bitdubai.fermat_dap_plugin.layer.digital_asset_transaction.asset_reception.developer.bitdubai.version_1.developer_utils.mocks.MockDigitalAssetMetadataForTesting;
import com.bitdubai.fermat_dap_plugin.layer.digital_asset_transaction.asset_reception.developer.bitdubai.version_1.structure.DigitalAssetReceptionVault;
import com.bitdubai.fermat_dap_plugin.layer.digital_asset_transaction.asset_reception.developer.bitdubai.version_1.structure.DigitalAssetReceptor;
import com.bitdubai.fermat_dap_plugin.layer.digital_asset_transaction.asset_reception.developer.bitdubai.version_1.structure.database.AssetReceptionDao;
import com.bitdubai.fermat_dap_plugin.layer.digital_asset_transaction.asset_reception.developer.bitdubai.version_1.structure.database.AssetReceptionDatabaseConstants;
import com.bitdubai.fermat_dap_plugin.layer.digital_asset_transaction.asset_reception.developer.bitdubai.version_1.structure.database.AssetReceptionDatabaseFactory;
import com.bitdubai.fermat_dap_plugin.layer.digital_asset_transaction.asset_reception.developer.bitdubai.version_1.structure.events.AssetReceptionMonitorAgent;
import com.bitdubai.fermat_dap_plugin.layer.digital_asset_transaction.asset_reception.developer.bitdubai.version_1.structure.events.AssetReceptionRecorderService;
import com.bitdubai.fermat_pip_api.layer.pip_user.device_user.exceptions.CantGetLoggedInDeviceUserException;
import com.bitdubai.fermat_pip_api.layer.pip_user.device_user.interfaces.DealsWithDeviceUser;
import com.bitdubai.fermat_pip_api.layer.pip_user.device_user.interfaces.DeviceUserManager;
import com.bitdubai.fermat_pip_api.layer.platform_service.error_manager.DealsWithErrors;
import com.bitdubai.fermat_pip_api.layer.platform_service.error_manager.ErrorManager;
import com.bitdubai.fermat_pip_api.layer.platform_service.error_manager.UnexpectedPluginExceptionSeverity;
import com.bitdubai.fermat_pip_api.layer.platform_service.event_manager.enums.EventType;
import com.bitdubai.fermat_pip_api.layer.platform_service.event_manager.interfaces.DealsWithEvents;
import com.bitdubai.fermat_pip_api.layer.platform_service.event_manager.interfaces.EventManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Created by Manuel Perez (darkpriestrelative@gmail.com) on 11/09/15.
 */
public class AssetReceptionPluginRoot implements AssetReceptionManager, DealsWithActorAssetIssuer, DealsWithActorAssetUser, DealsWithAssetUserWallet, DealsWithAssetTransmissionNetworkServiceManager,  DealsWithBitcoinNetwork, DealsWithDeviceUser, DatabaseManagerForDevelopers, DealsWithLogger, DealsWithErrors, DealsWithPluginDatabaseSystem, DealsWithPluginFileSystem, DealsWithEvents, LogManagerForDevelopers, Plugin, Service {

    static Map<String, LogLevel> newLoggingLevel = new HashMap<String, LogLevel>();
    AssetUserWalletManager assetUserWalletManager;
    AssetTransmissionNetworkServiceManager assetTransmissionNetworkServiceManager;
    Database assetReceptionDatabase;
    ErrorManager errorManager;
    EventManager eventManager;
    PluginDatabaseSystem pluginDatabaseSystem;
    UUID pluginId;
    PluginFileSystem pluginFileSystem;
    ServiceStatus serviceStatus= ServiceStatus.CREATED;
    AssetReceptionRecorderService assetReceptionRecorderService;
    ActorAssetUserManager actorAssetUserManager;
    ActorAssetIssuerManager actorAssetIssuerManager;
    AssetReceptionMonitorAgent assetReceptionMonitorAgent;
    DeviceUserManager deviceUserManager;
    LogManager logManager;
    DigitalAssetReceptionVault digitalAssetReceptionVault;
    BitcoinNetworkManager bitcoinNetworkManager;
    DigitalAssetReceptor digitalAssetReceptor;

    //TODO: Delete this log object
    Logger LOG = Logger.getGlobal();

    @Override
    public List<DeveloperDatabase> getDatabaseList(DeveloperObjectFactory developerObjectFactory) {
        AssetReceptionDeveloperDatabaseFactory assetReceptionDatabaseFactory=new AssetReceptionDeveloperDatabaseFactory(this.pluginDatabaseSystem, this.pluginId);
        return assetReceptionDatabaseFactory.getDatabaseList(developerObjectFactory);
    }

    @Override
    public List<DeveloperDatabaseTable> getDatabaseTableList(DeveloperObjectFactory developerObjectFactory, DeveloperDatabase developerDatabase) {
        return AssetReceptionDeveloperDatabaseFactory.getDatabaseTableList(developerObjectFactory);
    }

    @Override
    public List<DeveloperDatabaseTableRecord> getDatabaseTableContent(DeveloperObjectFactory developerObjectFactory, DeveloperDatabase developerDatabase, DeveloperDatabaseTable developerDatabaseTable) {
        Database database;
        try {
            database = this.pluginDatabaseSystem.openDatabase(pluginId, AssetReceptionDatabaseConstants.ASSET_RECEPTION_DATABASE);
            return AssetReceptionDeveloperDatabaseFactory.getDatabaseTableContent(developerObjectFactory, database, developerDatabaseTable);
        }catch (CantOpenDatabaseException cantOpenDatabaseException){
            /**
             * The database exists but cannot be open. I can not handle this situation.
             */
            FermatException e = new CantDeliverDatabaseException("Cannot open the database",cantOpenDatabaseException,"DeveloperDatabase: " + developerDatabase.getName(),"");
            this.errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_ASSET_RECEPTION_TRANSACTION, UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN,e);
        }
        catch (DatabaseNotFoundException databaseNotFoundException) {
            FermatException e = new CantDeliverDatabaseException("Database does not exists",databaseNotFoundException,"DeveloperDatabase: " + developerDatabase.getName(),"");
            this.errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_ASSET_RECEPTION_TRANSACTION,UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN,e);
        } catch(Exception exception){
            FermatException e = new CantDeliverDatabaseException("Unexpected Exception",exception,"DeveloperDatabase: " + developerDatabase.getName(),"");
            this.errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_ASSET_RECEPTION_TRANSACTION,UnexpectedPluginExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_PLUGIN,e);
        }
        // If we are here the database could not be opened, so we return an empty list
        return new ArrayList<>();
    }

    @Override
    public void setErrorManager(ErrorManager errorManager) {
        this.errorManager=errorManager;
    }

    @Override
    public void setPluginDatabaseSystem(PluginDatabaseSystem pluginDatabaseSystem) {
        this.pluginDatabaseSystem=pluginDatabaseSystem;
    }

    @Override
    public void setPluginFileSystem(PluginFileSystem pluginFileSystem) {
        this.pluginFileSystem=pluginFileSystem;
    }

    @Override
    public List<String> getClassesFullPath() {
        List<String> returnedClasses = new ArrayList<>();
        returnedClasses.add("com.bitdubai.fermat_dap_plugin.layer.digital_asset_transaction.asset_rception.developer.bitdubai.version_1.structure.events.AssetReceptionMonitorAgent");
        returnedClasses.add("com.bitdubai.fermat_dap_plugin.layer.digital_asset_transaction.asset_reception.developer.bitdubai.version_1.AssetReceptionPluginRoot");
        /**
         * I return the values.
         */
        return returnedClasses;
    }

    @Override
    public void setLoggingLevelPerClass(Map<String, LogLevel> newLoggingLevel) {
        /**
         * I will check the current values and update the LogLevel in those which is different
         */
        for (Map.Entry<String, LogLevel> pluginPair : newLoggingLevel.entrySet()) {
            /**
             * if this path already exists in the Root.bewLoggingLevel I'll update the value, else, I will put as new
             */
            if (AssetReceptionPluginRoot.newLoggingLevel.containsKey(pluginPair.getKey())) {
                AssetReceptionPluginRoot.newLoggingLevel.remove(pluginPair.getKey());
                AssetReceptionPluginRoot.newLoggingLevel.put(pluginPair.getKey(), pluginPair.getValue());
            } else {
                AssetReceptionPluginRoot.newLoggingLevel.put(pluginPair.getKey(), pluginPair.getValue());
            }
        }
    }

    @Override
    public void setId(UUID pluginId) {
        this.pluginId=pluginId;
    }

    private void createAssetReceptionTransactionDatabase() throws CantCreateDatabaseException {
        AssetReceptionDatabaseFactory databaseFactory = new AssetReceptionDatabaseFactory(this.pluginDatabaseSystem);
        assetReceptionDatabase = databaseFactory.createDatabase(pluginId, AssetReceptionDatabaseConstants.ASSET_RECEPTION_DATABASE);
    }

    @Override
    public void start() throws CantStartPluginException {
        //printSomething(">>> starting asset reception plugin");
        try{
            try {
                this.assetReceptionDatabase=this.pluginDatabaseSystem.openDatabase(pluginId, AssetReceptionDatabaseConstants.ASSET_RECEPTION_DATABASE);
            } catch (CantOpenDatabaseException |DatabaseNotFoundException e) {
                //printSomething("CREATING A PLUGIN DATABASE.");
                try {
                    createAssetReceptionTransactionDatabase();
                } catch (CantCreateDatabaseException innerException) {
                    throw new CantStartPluginException(CantCreateDatabaseException.DEFAULT_MESSAGE, innerException,"Starting Asset Reception plugin - "+this.pluginId, "Cannot open or create the plugin database");
                }
            }
            digitalAssetReceptionVault=new DigitalAssetReceptionVault(
                    pluginId,
                    pluginFileSystem,
                    errorManager);
            digitalAssetReceptionVault.setAssetUserWalletManager(this.assetUserWalletManager);
            printSomething("The wallet public key is hardcoded");
            digitalAssetReceptionVault.setWalletPublicKey("walletPublicKeyTest");
            digitalAssetReceptor=new DigitalAssetReceptor(this.errorManager, this.pluginId, this.pluginFileSystem);
            AssetReceptionDao assetReceptionDao=new AssetReceptionDao(this.pluginDatabaseSystem, this.pluginId);
            this.assetReceptionRecorderService =new AssetReceptionRecorderService(assetReceptionDao, eventManager);
            try{
                //I need to check if this works
                this.assetReceptionRecorderService.setAssetReceptionPluginRoot(this);
                this.assetReceptionRecorderService.start();
            } catch(CantStartServiceException exception){
                //This plugin must be stopped if this happens.
                this.serviceStatus = ServiceStatus.STOPPED;
                errorManager.reportUnexpectedPluginException(Plugins.BITDUBAI_ASSET_RECEPTION_TRANSACTION, UnexpectedPluginExceptionSeverity.DISABLES_THIS_PLUGIN, exception);
                throw new CantStartPluginException("Asset reception Event Recorded could not be started", exception, Plugins.BITDUBAI_ASSET_RECEPTION_TRANSACTION.getKey(), "The plugin event recorder is not started");
            }
            //testDeveloperDatabase();

        } catch (CantSetObjectException exception) {
            this.serviceStatus=ServiceStatus.STOPPED;
            throw new CantStartPluginException(CantStartPluginException.DEFAULT_MESSAGE, exception,"Starting Asset Reception plugin", "Cannot set an object, probably is null");
        } catch (CantExecuteDatabaseOperationException exception) {
            this.serviceStatus=ServiceStatus.STOPPED;
            throw new CantStartPluginException(CantStartPluginException.DEFAULT_MESSAGE, exception,"Starting Asset Reception plugin", "Cannot execute a database operation");
        } catch (CantStartServiceException exception) {
            this.serviceStatus=ServiceStatus.STOPPED;
            throw new CantStartPluginException(CantStartPluginException.DEFAULT_MESSAGE, exception,"Starting Asset Reception plugin", "Cannot start event recorder service");
        }/*catch(Exception exception){
            System.out.println("ASSET RECEPTION EXCEPTION TEST "+exception);
            exception.printStackTrace();
        }*/

        this.serviceStatus=ServiceStatus.STARTED;
        //testRaiseEvent();
    }

    @Override
    public void pause() {
        this.serviceStatus = ServiceStatus.PAUSED;
    }

    @Override
    public void resume() {
        this.serviceStatus = ServiceStatus.STARTED;
    }

    @Override
    public void stop() {
        this.serviceStatus = ServiceStatus.STOPPED;
    }

    @Override
    public ServiceStatus getStatus() {
        return this.serviceStatus;
    }

    @Override
    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    /**
     * This method will start the Monitor Agent that watches the asyncronic process registered in the asset distribution plugin
     * @throws CantGetLoggedInDeviceUserException
     * @throws CantSetObjectException
     * @throws CantStartAgentException
     */
    public void startMonitorAgent() throws CantGetLoggedInDeviceUserException, CantSetObjectException, CantStartAgentException {
        if(this.assetReceptionMonitorAgent ==null){
            String userPublicKey = this.deviceUserManager.getLoggedInDeviceUser().getPublicKey();
            this.assetReceptionMonitorAgent =new AssetReceptionMonitorAgent(this.eventManager,
                    this.pluginDatabaseSystem,
                    this.errorManager,
                    this.pluginId,
                    userPublicKey);
            this.assetReceptionMonitorAgent.setLogManager(this.logManager);
            this.assetReceptionMonitorAgent.setBitcoinNetworkManager(bitcoinNetworkManager);
            this.assetReceptionMonitorAgent.setDigitalAssetDistributionVault(this.digitalAssetReceptionVault);
            this.assetReceptionMonitorAgent.setAssetTransmissionManager(this.assetTransmissionNetworkServiceManager);
            this.assetReceptionMonitorAgent.start();
        }/*else{
            this.assetReceptionMonitorAgent.start();
        }*/
    }

    //TODO: DELETE THIS USELESS METHOD
    private void printSomething(String information){
        LOG.info("ASSET RECEPTION: " + information);
    }

    @Override
    public void setAssetTransmissionNetworkServiceManager(AssetTransmissionNetworkServiceManager assetTransmissionNetworkServiceManager) {
        this.assetTransmissionNetworkServiceManager = assetTransmissionNetworkServiceManager;
    }

    @Override
    public void setAssetUserManager(AssetUserWalletManager assetUserWalletManager) {
        this.assetUserWalletManager = assetUserWalletManager;
    }

    public static LogLevel getLogLevelByClass(String className) {
        try {
            /**
             * sometimes the classname may be passed dinamically with an $moretext
             * I need to ignore whats after this.
             */
            String[] correctedClass = className.split((Pattern.quote("$")));
            return AssetReceptionPluginRoot.newLoggingLevel.get(correctedClass[0]);
        } catch (Exception e) {
            /**
             * If I couldn't get the correct loggin level, then I will set it to minimal.
             */
            return DEFAULT_LOG_LEVEL;
        }
    }

    @Override
    public void setActorAssetUserManager(ActorAssetUserManager actorAssetUserManager) throws CantSetObjectException {
        this.actorAssetUserManager = actorAssetUserManager;
    }

    @Override
    public void setActorAssetIssuerManager(ActorAssetIssuerManager actorAssetIssuerManager) throws CantSetObjectException {
        this.actorAssetIssuerManager=actorAssetIssuerManager;
    }

    @Override
    public void setDeviceUserManager(DeviceUserManager deviceUserManager) {
        this.deviceUserManager=deviceUserManager;
    }

    @Override
    public void setLogManager(LogManager logManager) {
        this.logManager=logManager;
    }

    @Override
    public void setBitcoinNetworkManager(BitcoinNetworkManager bitcoinNetworkManager) {
        this.bitcoinNetworkManager=bitcoinNetworkManager;
    }

    private void testRaiseEvent(){
        printSomething("Start event test");
        FermatEvent eventToRaise = eventManager.getNewEvent(EventType.RECEIVED_NEW_DIGITAL_ASSET_METADATA_NOTIFICATION);
        eventToRaise.setSource(EventSource.NETWORK_SERVICE_ASSET_TRANSMISSION);
        eventManager.raiseEvent(eventToRaise);
        printSomething("End event test");
    }

    private void testDeveloperDatabase() throws CantExecuteDatabaseOperationException, CantDefineContractPropertyException, CantPersistDigitalAssetException {
        System.out.println("START TEST DEVELOPER DATABASE ASSET RECEPTION");
        MockDigitalAssetMetadataForTesting mockDigitalAssetMetadataForTesting=new MockDigitalAssetMetadataForTesting();
        System.out.println("ASSET RECEPTION MOCKED DAM:"+mockDigitalAssetMetadataForTesting);
        AssetReceptionDao assetReceptionDao=new AssetReceptionDao(pluginDatabaseSystem,pluginId);
        assetReceptionDao.persistDigitalAsset(
                mockDigitalAssetMetadataForTesting.getGenesisTransaction(),
                "testLocalStorage",
                mockDigitalAssetMetadataForTesting.getDigitalAssetHash(),
                "testReceiverPublicKey");
    }

}
