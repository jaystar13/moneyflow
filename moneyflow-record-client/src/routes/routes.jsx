import { createBrowserRouter } from "react-router-dom";
import ErrorPage from "../error-page";
import Root from "../components/root";
import CodeType from "../components/codeType/codeTypeContainer";
import CodeContainer from "../components/code/codeContainer";
import FinancialCompanyContainer from "../components/financialCompany/financialCompanyContainer";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: "codeType",
        element: <CodeType />,
      },
      {
        path: "code",
        element: <CodeContainer />,
      },
      {
        path: "financialCompany",
        element: <FinancialCompanyContainer />,
      },
    ],
  },
]);

export default router;
