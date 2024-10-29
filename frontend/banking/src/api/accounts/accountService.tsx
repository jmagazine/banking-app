import { configDotenv } from "dotenv";
configDotenv({ path: "src/api/.env.dev" });
let API_BASE_URL = process.env.API_BASE_URL;
import { Account, CheckingsAccount, SavingsAccount } from "./account";

export interface GetAccountsOwnedByUserParams {
  userId: Number;
}

const getAccountsOwnedByUser = async (
  params: GetAccountsOwnedByUserParams,
): Promise<Account[] | any> => {
  const response = await fetch(
    `${API_BASE_URL}/accounts/user/${encodeURIComponent(params["userId"].toString())}`,
  );
  if (!response.ok) {
    const error = await response.text();
    console.log(`Failed to retrieve account information: ${error}`);
    return { error: error };
  }
  const accountsResponseList = await response.json();
  const accounts: Account[] = accountsResponseList.map(
    (account: any) => account as Account,
  );
  console.log(`Accounts: ${JSON.stringify(accounts)}`);
  return accounts;
};
export default getAccountsOwnedByUser;
