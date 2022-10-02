import { createBrowserRouter } from "react-router-dom";
import ErrorPage from "../error-page";
import Root from "../components/root";
import CodeType from "../components/codeType";
import CodeTypeForm, {
  action as codeTypeFormAction,
} from "../components/codeTypeForm";

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
        path: "codeType/add",
        element: <CodeTypeForm />,
        action: codeTypeFormAction,
      },
    ],
  },
]);

export default router;
