import { configDotenv } from "dotenv";
configDotenv({ path: "src/api/.env.dev" });
let API_BASE_URL = process.env.API_BASE_URL;
import { Account, CheckingsAccount, SavingsAccount } from "./account";

export interface GetAccountsOwnedByUserParams {
  ownerId: number;
}

const getAccountsOwnedByUser = async (
  params: GetAccountsOwnedByUserParams,
): Promise<Account[] | any> => {
  const response = await fetch(
    `${API_BASE_URL}/accounts?ownerid=${encodeURIComponent(params["ownerId"])}`,
  );
  if (!response.ok) {
    const error = await response.text();
    console.log(`Failed to retrieve account information: ${error}`);
    return { error: error };
  }
  const accountsArray: any[] = await response.json();
  const accounts: Account[] = accountsArray.map((account: any) => ({
    id: account.id,
    ownerId: account.ownerId,
    balanceCents: account.balance,
    creationDate: account.creationDate,
    maxDeposit: account.maxDeposit,
  }));
  return accounts;
};
export default getAccountsOwnedByUser;
