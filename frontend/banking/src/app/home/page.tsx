"use client";
import { ApiError } from "next/dist/server/api-utils";
import { useEffect, useState } from "react";
import "../../api/accounts/accountService";
import getAccountsOwnedByUser, {
  GetAccountsOwnedByUserParams,
} from "../../api/accounts/accountService";
import "../../api/accounts/account";
import { error } from "console";
import { Account } from "../../api/accounts/account";

export interface HomePageProps {
  ownerId: number;
}

interface HomepageProps {
  userId: number;
}

const API_BASE_URL = process.env.API_BASE_URL;

export default function HomePage(props: HomePageProps) {
  const [accounts, setAccounts] = useState<Account[]>([]);
  useEffect(() => {
    getAccountsOwnedByUser(props as GetAccountsOwnedByUserParams).then(
      async (res) => {
        if (res.error) {
          console.log(`Failed to find account: ${res.error}`);
        } else {
          const accountsArray: Account[] = res;
          setAccounts(accountsArray);
          console.log(accountsArray);
        }
      },
    );
  }, []);
  <nav className="navbar navbar-expand-lg bg-body-tertiary">
    <div className="container-fluid">
      <a className="navbar-brand" href="#">
        Navbar
      </a>
      <button
        className="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarSupportedContent">
        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
          <li className="nav-item">
            <a className="nav-link active" aria-current="page" href="#">
              Home
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#">
              Link
            </a>
          </li>
          <li className="nav-item dropdown">
            <a
              className="nav-link dropdown-toggle"
              href="#"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Dropdown
            </a>
            <ul className="dropdown-menu">
              <li>
                <a className="dropdown-item" href="#">
                  Action
                </a>
              </li>
              <li>
                <a className="dropdown-item" href="#">
                  Another action
                </a>
              </li>
              <li>
                <hr className="dropdown-divider" />
              </li>
              <li>
                <a className="dropdown-item" href="#">
                  Something else here
                </a>
              </li>
            </ul>
          </li>
          <li className="nav-item">
            <a className="nav-link disabled" aria-disabled="true">
              Disabled
            </a>
          </li>
        </ul>
        <form className="d-flex" role="search">
          <input
            className="form-control me-2"
            type="search"
            placeholder="Search"
            aria-label="Search"
          />
          <button className="btn btn-outline-success" type="submit">
            Search
          </button>
        </form>
      </div>
    </div>
  </nav>;
}
