import { useEffect, useState } from "react";
import "./Home.css";

const Home = () => {
  const [customerList, setCustomerList] = useState([]);
  const [selectedCustomer, setSelectedCustomer] = useState(null);
  const [selectedAccount, setSelectedAccount] = useState(null);
  const [initialCredit, setInitialCredit] = useState(0);

  const backendBaseUrl = "http://localhost:8082/api/v1";

  const updateCustomerAccounts = (newAccount) => {
    setSelectedCustomer((prev) => ({
      ...prev,
      accounts: [...prev.accounts, newAccount],
    }));
  };

  const mockCustomerList = [
    { id: "1", name: "Juan Pérez" },
    { id: "2", name: "María García" },
    { id: "3", name: "Carlos López" },
  ];

  const mockCustomerDetails = {
    id: "1",
    name: "Juan Pérez",
    surName: "Pérez",
    accounts: [
      {
        id: "Acc001",
        balance: 5000,
        transactions: [
          {
            id: "T001",
            type: "in",
            amount: 600,
            prevBalance: 1000,
            newBalance: 1600,
            date: "11/11/2024",
            subject: "test transaction",
          },
          {
            id: "T002",
            type: "out",
            amount: 600,
            prevBalance: 1000,
            newBalance: 400,
            date: "11/11/2024",
            subject: "test  another transaction",
          },
        ],
      },
      {
        id: "Acc002",
        balance: 3000,
        transactions: [
          { id: "T003", type: "in" },
          { id: "T004", type: "out" },
        ],
      },
    ],
  };

  useEffect(() => {
    // Fetch para obtener la lista de clientes
    fetch(`${backendBaseUrl}/customers`)
       .then((response) => response.json())
       .then((data) => setCustomerList(data))
       .catch((error) => console.error("Error fetching customers:", error));
//    setCustomerList(mockCustomerList);
  }, []);

  useEffect(() => {
    if (selectedCustomer?.id) {
      // Fetch para obtener los detalles del cliente seleccionado
      fetch(`${backendBaseUrl}/customers/${selectedCustomer.id}`)
         .then((response) => response.json())
         .then((data) => setSelectedCustomer(data))
         .catch((error) => console.error("Error fetching customer details:", error));
      /*if (selectedCustomer.id === "1") {
        setSelectedCustomer(mockCustomerDetails);
      }*/
    }
  }, [selectedCustomer?.id]);

  const handleCustomerChange = (e) => {
    //fetch
    const currentCustomer = customerList.find(
      (customer) => customer.name === e.target.value
    );
    setSelectedCustomer(currentCustomer);
    setSelectedAccount(null);
    setInitialCredit(0);
  };

  const handleAccountChange = (e) => {
    console.log("Account change:", e.target.value);
    console.log("Selected customer:", selectedCustomer);

    fetch(`${backendBaseUrl}/accounts/${e.target.value}`)
    .then((response) => response.json())
    .then((data) => setSelectedAccount(data))
    .catch((error) => console.error("Error fetching customer details:", error));

   /* const currentAccount = selectedCustomer?.accounts?.find(
      (account) => account.accountId === e.target.value
    );
    setSelectedAccount(currentAccount);*/
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const data = {
      customerId: selectedCustomer.id,
      initialCredit: Number(initialCredit),
    };

    const newAccount = {
      id: `Acc${Math.floor(Math.random() * 100000)}`,
      balance: 1000,
      transactions: [
        { id: "T5", type: "in" },
        { id: "T6", type: "out" },
      ],
    };
    // Simulación de la creación de cuenta (fetch real)
    fetch(`${backendBaseUrl}/accounts`, {
       method: 'POST',
       headers: {
         'Content-Type': 'application/json',
       },
       body: JSON.stringify(data),
     })
       .then((response) => response.json())
       .then((newAccount) => {
         console.log("Account created successfully:", newAccount);
         updateCustomerAccounts(newAccount);
       })
       .catch((error) => console.error("Error creating account:", error));

   /* setTimeout(() => {
      console.log("Account created successfully:", newAccount);
      updateCustomerAccounts(newAccount);
    }, 1000);*/
  };

  return (
    <div className="container">
      <h1 className="title">Create Account</h1>

      <div className="form-group">
        <label>Select a customer:</label>
        <select onChange={handleCustomerChange} defaultValue="">
          <option value="" disabled>
            Choose an option
          </option>
          {customerList.map((customer) => (
            <option key={customer.id} value={customer.name}>
              {customer.name} {customer.surname}
            </option>
          ))}
        </select>
      </div>

      {selectedCustomer && (
        <div className="customer-info">
          <h2>Customer Information</h2>
          <p>
            <strong>Name:</strong> {selectedCustomer.name}
          </p>
          <p>
            <strong>Surname:</strong> {selectedCustomer.surname}
          </p>

          <div className="form-group">
            <label>Select an account:</label>
            <select
              onChange={handleAccountChange}
              defaultValue=""
            >
              <option value="" disabled>
                Choose an option
              </option>
              {selectedCustomer.accounts?.map((account) => (
                <option key={account.accountId} value={account.accountId}>
                  {account.accountId}
                </option>
              ))}
            </select>
          </div>

          {selectedAccount && (
            <>
              <p>
                <strong>Balance:</strong> {selectedAccount.balance}
              </p>

              <label>
                <strong>Transactions:</strong>
              </label>
              <table style={{ width: "700px" }}>
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Amount</th>
                    <th>Date</th>
                  </tr>
                </thead>
                <tbody style={{ textAlign: "center" }}>
                  {selectedAccount.transactions.map((transaction) => (
                    <tr key={transaction.transactionId}>
                      <td>{transaction.transactionId}</td>
                      <td>{transaction.amount}</td>
                      <td>{transaction.timestamp}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </>
          )}

          <form onSubmit={handleSubmit} className="account-form">
            <div className="form-group">
              <label>
                Customer ID:
                <input type="text" value={selectedCustomer.id ?? ""} disabled />
              </label>
            </div>

            <div className="form-group">
              <label>
                Initial Credit:
                <input
                  type="number"
                  placeholder="Enter amount"
                  value={initialCredit ?? 0}
                  onChange={(e) => setInitialCredit(e.target.value)}
                  required
                />
              </label>
            </div>

            <button type="submit" className="submit-button">
              Create Account
            </button>
          </form>
        </div>
      )}
    </div>
  );
};

export default Home;
