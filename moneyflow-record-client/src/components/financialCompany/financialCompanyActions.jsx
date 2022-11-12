import { Button } from "antd";

export default function FinancialCompanyActions({ configure }) {
  const showFormLayer = () => {
    console.log("showFormLayer");
  };
  return (
    <div>
      {!configure.useModal ? (
        <Button
          type="primary"
          style={{ float: "right", marginBottom: 16 }}
          onClick={showFormLayer}
        >
          Add
        </Button>
      ) : (
        ""
      )}
    </div>
  );
}
