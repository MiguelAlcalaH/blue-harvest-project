import { useEffect, useState } from "react";
import "./Home.css";

const Home = () => {
  const [customerList, setCustomerList] = useState([]);
  const [selectecCustomer, setSelectecCustomer] = useState();
  const [selectedAccount, setSelectedAccount] = useState("");
  const [initialCredit, setInitialCredit] = useState();

  const updateCustomerAccounts = (newAccount) => {
    setSelectecCustomer((prev) => ({
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
    // fetch('/v1/customers')
    //   .then((response) => response.json())
    //   .then((data) => setCustomerList(data))
    //   .catch((error) => console.error("Error fetching customers:", error));
    setCustomerList(mockCustomerList);
  }, []);

  useEffect(() => {
    if (selectecCustomer?.id) {
      // Fetch para obtener los detalles del cliente seleccionado
      // fetch(`/v1/customer/${selectecCustomer.id}`)
      //   .then((response) => response.json())
      //   .then((data) => setSelectecCustomer(data))
      //   .catch((error) => console.error("Error fetching customer details:", error));
      if (selectecCustomer.id === "1") {
        setSelectecCustomer(mockCustomerDetails);
      }
    }
  }, [selectecCustomer?.id]);

  const handleCustomerChange = (e) => {
    //fetch
    const currentCustomer = customerList.find(
      (customer) => customer.name === e.target.value
    );
    setSelectecCustomer(currentCustomer);
    setSelectedAccount(null);
    setInitialCredit(null);
  };

  const handleAccountChange = (e) => {
    const currentAccount = selectecCustomer?.accounts?.find(
      (account) => account.id === e.target.value
    );
    setSelectedAccount(currentAccount);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const data = {
      customerId: selectecCustomer.id,
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
    // fetch('http://localhost:8080/v1/account', {
    //   method: 'POST',
    //   headers: {
    //     'Content-Type': 'application/json',
    //   },
    //   body: JSON.stringify(data),
    // })
    //   .then((response) => response.json())
    //   .then((newAccount) => {
    //     console.log("Account created successfully:", newAccount);
    //     updateCustomerAccounts(newAccount);
    //   })
    //   .catch((error) => console.error("Error creating account:", error));

    setTimeout(() => {
      console.log("Account created successfully:", newAccount);
      updateCustomerAccounts(newAccount);
    }, 1000);
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
              {customer.name}
            </option>
          ))}
        </select>
      </div>

      {selectecCustomer && (
        <div className="customer-info">
          <h2>Customer Information</h2>
          <p>
            <strong>Name:</strong> {selectecCustomer.name}
          </p>
          <p>
            <strong>Surname:</strong> {selectecCustomer.surName}
          </p>

          <div className="form-group">
            <label>Select an account:</label>
            <select
              onChange={handleAccountChange}
              value={selectedAccount?.id || ""}
            >
              <option value="" disabled>
                Choose an option
              </option>
              {selectecCustomer.accounts?.map((account) => (
                <option key={account.id} value={account.id}>
                  {account.id}
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
                    <th>Type</th>
                    <th>Amount</th>
                    <th>Previous Balance</th>
                    <th>New Balance</th>
                    <th>Date</th>
                    <th>Subject</th>
                  </tr>
                </thead>
                <tbody style={{ textAlign: "center" }}>
                  {selectedAccount.transactions.map((transaction) => (
                    <tr key={transaction.id}>
                      <td>{transaction.id}</td>
                      <td>{transaction.type}</td>
                      <td>{transaction.amount}</td>
                      <td>{transaction.prevBalance}</td>
                      <td>{transaction.newBalance}</td>
                      <td>{transaction.date}</td>
                      <td>{transaction.subject}</td>
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
                <input type="text" value={selectecCustomer.id ?? ""} disabled />
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
