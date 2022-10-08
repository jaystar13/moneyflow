import { createBrowserRouter } from "react-router-dom";
import ErrorPage from "../error-page";
import Root from "../components/root";
import CodeType from "../components/codeType/codeTypeContainer";
import CodeTypeForm from "../components/codeType/codeTypeForm";

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
    ],
  },
]);

export default router;
