CREATE TABLE Budgets (
                         id VARCHAR(36) PRIMARY KEY,
                         budgetName VARCHAR(255) NOT NULL,
                         totalIncome DECIMAL(10, 2) NOT NULL,
                         totalExpense DECIMAL(10, 2) NOT NULL,
                         startDate DATE NOT NULL,
                         endDate DATE NOT NULL,
                         savingsGoal DECIMAL(10, 2) NOT NULL,
                         currentBalance DECIMAL(10, 2) NOT NULL,
                         status VARCHAR(50) NOT NULL,
                         notifications BOOLEAN NOT NULL,
                         version INT
);
CREATE TABLE Categories (
                            categoryID VARCHAR(36) PRIMARY KEY,
                            categoryName VARCHAR(255) NOT NULL,
                            budgetedAmount DECIMAL(10, 2) NOT NULL,
                            spentAmount DECIMAL(10, 2) NOT NULL,
                            budgetID VARCHAR(36) NOT NULL,
                            FOREIGN KEY (budgetID) REFERENCES Budgets(id)
);
CREATE TABLE Transactions (
                              transactionID VARCHAR(36) PRIMARY KEY,
                              date DATE NOT NULL,
                              amount DECIMAL(10, 2) NOT NULL,
                              category VARCHAR(36) NOT NULL,
                              description TEXT NOT NULL,
                              budgetID VARCHAR(36) NOT NULL,
                              FOREIGN KEY (budgetID) REFERENCES Budgets(id),
                              FOREIGN KEY (category) REFERENCES Categories(categoryID)
);
