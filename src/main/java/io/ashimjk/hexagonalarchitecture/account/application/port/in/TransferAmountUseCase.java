package io.ashimjk.hexagonalarchitecture.account.application.port.in;

public interface TransferAmountUseCase {

    boolean transfer(TransferAmountCommand command);

}
