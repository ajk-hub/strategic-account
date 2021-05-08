package io.ashimjk.strategicaccount.account.application.port.in;

public interface TransferAmountUseCase {

    boolean transfer(TransferAmountCommand command);

}
