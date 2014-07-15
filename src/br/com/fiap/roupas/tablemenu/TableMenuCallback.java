package br.com.fiap.roupas.tablemenu;

public interface TableMenuCallback
{
	public void goToExecuteDeliveries();
	public void goToFinalizedDeliveries();
	public void goToDeliveriesForTomorrow();
	public void cancelDeliveries();
	public void registerReason();
	public void deliveriesSynchronized();
	public void databaseCleaned();
	public void changePassword();
	public void logout();
}
