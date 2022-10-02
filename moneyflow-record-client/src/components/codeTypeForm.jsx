import { Form, redirect, useNavigate } from "react-router-dom";
import { createCodeType } from "../api/api";

export async function action({ request }) {
  const formData = await request.formData();
  const codeTypes = Object.fromEntries(formData);
  await createCodeType(codeTypes);
  return redirect(`/codeType`);
}

export default function CodeTypeForm() {
  const navigate = useNavigate();

  return (
    <div>
      <h1>Add Code Type</h1>
      <Form method="post" id="codeType-form">
        <p>
          <input placeholder="명칭" type="text" name="codeTypeNm" />
        </p>
        <p>
          <button type="submit">Save</button>
          <button
            type="submit"
            onClick={() => {
              navigate(-1);
            }}
          >
            Cancel
          </button>
        </p>
      </Form>
    </div>
  );
}
