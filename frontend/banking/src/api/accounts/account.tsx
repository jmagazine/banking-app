export interface Account {
  accountType: string;
  id: number;
  userId: number;
  balance: number;
  creationDate: string; // can be converted using
  maxDeposit: number;
}

export interface CheckingsAccount extends Account {
  overdraftLimit: number | null;
}
export interface SavingsAccount extends Account {
  interestRate: number | null;
}
