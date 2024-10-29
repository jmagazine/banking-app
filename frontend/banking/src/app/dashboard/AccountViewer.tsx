import { useEffect, useState } from "react";
import { Account } from "../../api/accounts/account";
import "./AccountViewer.css";
import { Box, Modal, Typography } from "@mui/material";

interface AccountComponentProps {
  account: Account;
}

interface AccountViewerProps {
  accounts: Account[];
}

function AccountViewer(props: AccountViewerProps) {
  return (
    <div className="account-viewer-container ">
      <div className="bank-accounts-label">
        <h3>Bank Accounts</h3>
      </div>
      <div className="separator-line"></div>
      {props.accounts &&
        props.accounts.map((account) => (
          <>
            <div className="separator-line"></div>
            <AccountComponent key={account.id} account={account} />
          </>
        ))}
    </div>
  );
}

function AccountComponent(props: AccountComponentProps) {
  // currentBalance will always be the balance in CENTS.
  const [currentBalance, setCurrentBalance] = useState(0);
  const [sendMoneyModalOpen, setSendMoneyModalOpen] = useState(false);
  const [transferMoneyModalOpen, setTransferMoneyModalOpen] = useState(false);
  useEffect(() => {
    setCurrentBalance(props.account.balance);
  }, []);
  const handleSendMoney = (e: React.MouseEvent) => {
    e.preventDefault();
    setSendMoneyModalOpen(true);
  };

  function sendMoneyModal() {
    const modalStyle = {
      position: "absolute",
      top: "50%",
      left: "50%",
      transform: "translate(-50%, -50%)",
      width: 400,
      bgcolor: "background.paper",
      border: "2px solid #000",
      boxShadow: 24,
      p: 4,
    };
    return (
      <>
        <Modal
          open={sendMoneyModalOpen}
          onClose={() => setSendMoneyModalOpen(false)}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
        >
          <Box sx={modalStyle}>
            <Typography id="modal-modal-title" variant="h6" component="h2">
              Text in a modal
            </Typography>
            <Typography id="modal-modal-description" sx={{ mt: 2 }}>
              Duis mollis, est non commodo luctus, nisi erat porttitor ligula.
            </Typography>
          </Box>
        </Modal>
      </>
    );
  }

  const handleTransferFunds = (e: React.MouseEvent) => {
    e.preventDefault();
    setTransferMoneyModalOpen(true);
  };

  if (sendMoneyModalOpen) {
    return sendMoneyModal();
  } else if (transferMoneyModalOpen) {
    return <></>;
  } else {
    return (
      <div className="account-container">
        <div className="account-header">
          <header className="account-type-container">
            <h1>{props.account.accountType}</h1>
          </header>
          <div className="buttons">
            <div className="button" onClick={handleSendMoney}>
              <p>Send Money</p>
            </div>
            <div className="button" onClick={handleTransferFunds}>
              <p>Transfer Funds</p>
            </div>
          </div>
        </div>
        <div className="balance-details">
          <h2>
            {(props.account.balance / 100).toLocaleString("en-US", {
              style: "currency",
              currency: "USD",
            })}
          </h2>
          <h3>Current balance</h3>
        </div>
      </div>
    );
  }
}

export default AccountViewer;
