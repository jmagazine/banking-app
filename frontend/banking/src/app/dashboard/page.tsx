"use client";
import "../globals.css";
import "./page.css";
import { useEffect, useState } from "react";
import "../../api/accounts/accountService";
import getAccountsOwnedByUser from "../../api/accounts/accountService";
import AccountViewer from "./AccountViewer";
import "../../api/accounts/account";
import { Account } from "../../api/accounts/account";
import { useRouter } from "next/navigation";

export interface DashboardPageProps {
  userId: number;
}

const API_BASE_URL = process.env.API_BASE_URL;

function Navbar() {
  return (
    <nav className="navbar">
      <h1>Dashboard</h1>
    </nav>
  );
}

function DashboardPage() {
  const router = useRouter();
  const [accounts, setAccounts] = useState<Account[]>([]);
  useEffect(() => {
    const userId = sessionStorage.getItem("userId");
    if (!userId) {
      router.push("");
    } else {
      getAccountsOwnedByUser({ userId: Number.parseInt(userId) }).then(
        async (res) => {
          if (res.error) {
            console.log(`Failed to find account: ${res.error}`);
          } else {
            const accountsArray: Account[] = await res;
            setAccounts(accountsArray);
          }
        },
      );
    }
  }, []);
  return (
    <div className="dashboard-page-container">
      <Navbar />
      <AccountViewer accounts={accounts} />
    </div>
  );
}

export default DashboardPage;
