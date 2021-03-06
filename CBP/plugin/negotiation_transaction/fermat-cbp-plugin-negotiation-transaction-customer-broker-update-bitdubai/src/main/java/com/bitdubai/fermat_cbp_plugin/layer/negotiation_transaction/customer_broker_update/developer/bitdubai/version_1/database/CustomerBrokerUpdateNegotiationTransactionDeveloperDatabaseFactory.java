package com.bitdubai.fermat_cbp_plugin.layer.negotiation_transaction.customer_broker_update.developer.bitdubai.version_1.database;

import com.bitdubai.fermat_api.DealsWithPluginIdentity;
import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperDatabase;
import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperDatabaseTable;
import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperDatabaseTableRecord;
import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperObjectFactory;
import com.bitdubai.fermat_api.layer.osa_android.database_system.Database;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseRecord;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTable;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTableRecord;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DealsWithPluginDatabaseSystem;
import com.bitdubai.fermat_api.layer.osa_android.database_system.PluginDatabaseSystem;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantCreateDatabaseException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantLoadTableToMemoryException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.DatabaseNotFoundException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantOpenDatabaseException;
import com.bitdubai.fermat_cbp_plugin.layer.negotiation_transaction.customer_broker_update.developer.bitdubai.version_1.exceptions.CantInitializeCustomerBrokerUpdateNegotiationTransactionDatabaseException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The Class <code>com.bitdubai.fermat_cbp_plugin.layer.negotiation_transaction.customer_broker_update.developer.bitdubai.version_1.database.CustomerBrokerUpdateNegotiationTransactionDeveloperDatabaseFactory</code> have
 * contains the methods that the Developer Database Tools uses to show the information.
 * <p/>
 *
 * Created by Yordin Alayn - (y.alayn@gmail.com) on 17/12/15.
 *
 * @version 1.0
 * @since Java JDK 1.7
 */

public class CustomerBrokerUpdateNegotiationTransactionDeveloperDatabaseFactory {//implements DealsWithPluginDatabaseSystem, DealsWithPluginIdentity {

    /**
     * DealsWithPluginDatabaseSystem Interface member variables.
     */
    PluginDatabaseSystem pluginDatabaseSystem;

    /**
     * DealsWithPluginIdentity Interface member variables.
     */
    UUID pluginId;


    Database database;

    /**
     * Constructor
     *
     * @param pluginDatabaseSystem
     * @param pluginId
     */
    public CustomerBrokerUpdateNegotiationTransactionDeveloperDatabaseFactory(PluginDatabaseSystem pluginDatabaseSystem, UUID pluginId) {
        this.pluginDatabaseSystem = pluginDatabaseSystem;
        this.pluginId = pluginId;
    }

    /**
     * This method open or creates the database i'll be working with
     *
     * @throws CantInitializeCustomerBrokerUpdateNegotiationTransactionDatabaseException
     */
    public void initializeDatabase() throws CantInitializeCustomerBrokerUpdateNegotiationTransactionDatabaseException {
        try {

             /*
              * Open new database connection
              */
            database = this.pluginDatabaseSystem.openDatabase(pluginId, pluginId.toString());

        } catch (CantOpenDatabaseException cantOpenDatabaseException) {

             /*
              * The database exists but cannot be open. I can not handle this situation.
              */
            throw new CantInitializeCustomerBrokerUpdateNegotiationTransactionDatabaseException(cantOpenDatabaseException.getMessage());

        } catch (DatabaseNotFoundException e) {

             /*
              * The database no exist may be the first time the plugin is running on this device,
              * We need to create the new database
              */
            CustomerBrokerUpdateNegotiationTransactionDatabaseFactory customerBrokerUpdateNegotiationTransactionDatabaseFactory = new CustomerBrokerUpdateNegotiationTransactionDatabaseFactory(pluginDatabaseSystem);

            try {
                  /*
                   * We create the new database
                   */
                database = customerBrokerUpdateNegotiationTransactionDatabaseFactory.createDatabase(pluginId, pluginId.toString());
            } catch (CantCreateDatabaseException cantCreateDatabaseException) {
                  /*
                   * The database cannot be created. I can not handle this situation.
                   */
                throw new CantInitializeCustomerBrokerUpdateNegotiationTransactionDatabaseException(cantCreateDatabaseException.getMessage());
            }
        }
    }


    public List<DeveloperDatabase> getDatabaseList(DeveloperObjectFactory developerObjectFactory) {
        /**
         * I only have one database on my plugin. I will return its name.
         */
        List<DeveloperDatabase> databases = new ArrayList<DeveloperDatabase>();
        databases.add(developerObjectFactory.getNewDeveloperDatabase("Customer Broker Update", this.pluginId.toString()));
        return databases;
    }


    public List<DeveloperDatabaseTable> getDatabaseTableList(DeveloperObjectFactory developerObjectFactory) {
        List<DeveloperDatabaseTable> tables = new ArrayList<DeveloperDatabaseTable>();

        /**
         * Table Customer Broker Update columns.
         */
        List<String> customerBrokerUpdateColumns = new ArrayList<String>();

        customerBrokerUpdateColumns.add(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_TRANSACTION_ID_COLUMN_NAME);
        customerBrokerUpdateColumns.add(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_NEGOTIATION_ID_COLUMN_NAME);
        customerBrokerUpdateColumns.add(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_PUBLIC_KEY_BROKER_COLUMN_NAME);
        customerBrokerUpdateColumns.add(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_PUBLIC_KEY_CUSTOMER_COLUMN_NAME);
        customerBrokerUpdateColumns.add(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_STATUS_TRANSACTION_COLUMN_NAME);
        customerBrokerUpdateColumns.add(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_STATUS_NEGOTIATION_COLUMN_NAME);
        customerBrokerUpdateColumns.add(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_STATE_TRANSMISSION_COLUMN_NAME);
        customerBrokerUpdateColumns.add(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_NEGOTIATION_TYPE_COLUMN_NAME);
        customerBrokerUpdateColumns.add(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_NEGOTIATION_XML_COLUMN_NAME);
        customerBrokerUpdateColumns.add(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_TIMESTAMP_COLUMN_NAME);
        customerBrokerUpdateColumns.add(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_SEND_TRANSACTION_COLUMN_NAME);
        customerBrokerUpdateColumns.add(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_CONFIRM_TRANSACTION_COLUMN_NAME);

        /**
         * Table Customer Broker Update addition.
         */
        DeveloperDatabaseTable customerBrokerUpdateTable = developerObjectFactory.getNewDeveloperDatabaseTable(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_TABLE_NAME, customerBrokerUpdateColumns);
        tables.add(customerBrokerUpdateTable);

        /**
         * Table Customer Broker New Event columns.
         */
        List<String> customerBrokerNewEventColumns = new ArrayList<String>();

        customerBrokerNewEventColumns.add(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_EVENT_ID_COLUMN_NAME);
        customerBrokerNewEventColumns.add(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_EVENT_TYPE_COLUMN_NAME);
        customerBrokerNewEventColumns.add(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_EVENT_SOURCE_COLUMN_NAME);
        customerBrokerNewEventColumns.add(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_EVENT_STATUS_COLUMN_NAME);
        customerBrokerNewEventColumns.add(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_EVENT_TIMESTAMP_COLUMN_NAME);
        /**
         * Table Customer Broker New Event addition.
         */
        DeveloperDatabaseTable customerBrokerNewEventTable = developerObjectFactory.getNewDeveloperDatabaseTable(CustomerBrokerUpdateNegotiationTransactionDatabaseConstants.CUSTOMER_BROKER_UPDATE_EVENT_TABLE_NAME, customerBrokerNewEventColumns);
        tables.add(customerBrokerNewEventTable);



        return tables;
    }


    public List<DeveloperDatabaseTableRecord> getDatabaseTableContent(DeveloperObjectFactory developerObjectFactory, DeveloperDatabaseTable developerDatabaseTable) {
        /**
         * Will get the records for the given table
         */
        List<DeveloperDatabaseTableRecord> returnedRecords = new ArrayList<DeveloperDatabaseTableRecord>();


        /**
         * I load the passed table name from the SQLite database.
         */
        DatabaseTable selectedTable = database.getTable(developerDatabaseTable.getName());
        try {
            selectedTable.loadToMemory();
        } catch (CantLoadTableToMemoryException cantLoadTableToMemory) {
            /**
             * if there was an error, I will returned an empty list.
             */
            return returnedRecords;
        }

        List<DatabaseTableRecord> records = selectedTable.getRecords();
        List<String> developerRow = new ArrayList<String>();
        for (DatabaseTableRecord row : records) {
            /**
             * for each row in the table list
             */
            for (DatabaseRecord field : row.getValues()) {
                /**
                 * I get each row and save them into a List<String>
                 */
                developerRow.add(field.getValue().toString());
            }
            /**
             * I create the Developer Database record
             */
            returnedRecords.add(developerObjectFactory.getNewDeveloperDatabaseTableRecord(developerRow));
        }


        /**
         * return the list of DeveloperRecords for the passed table.
         */
        return returnedRecords;
    }

//    @Override
//    public void setPluginDatabaseSystem(PluginDatabaseSystem pluginDatabaseSystem) {
//        this.pluginDatabaseSystem = pluginDatabaseSystem;
//    }
//
//    @Override
//    public void setPluginId(UUID pluginId) {
//        this.pluginId = pluginId;
//    }
}