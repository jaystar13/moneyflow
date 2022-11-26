import FinancialCompanyItems from "./financialCompanyItems";
import { Modal, Button } from "antd";

export default function FinancialCompanyForm({
  configure,
  data,
  reRender,
  callbackOnAdd,
}) {
  const callbackOnSave = () => {
    modalClose();
  };

  const reRendering = () => {
    if (reRender) {
      const { render, setRender } = reRender;
      setRender(!render);
    }
  };

  const modalClose = () => {
    if (configure.modalOpen) {
      configure.modalOpen = false;
      reRendering();
    }
  };

  const onAdd = () => {
    callbackOnAdd();
  };

  return (
    <div>
      {configure.useModal ? (
        <Modal
          open={configure.modalOpen}
          onCancel={modalClose}
          footer={[
            <Button key="cancel" onClick={modalClose}>
              Cancel
            </Button>,
          ]}
        >
          <FinancialCompanyItems data={data} callbackOnSave={callbackOnSave} />
        </Modal>
      ) : (
        <FinancialCompanyItems data={data} callbackOnSave={callbackOnSave} />
      )}
      {configure.useModal ? (
        <Button
          type="primary"
          style={{ float: "right", marginBottom: 16 }}
          onClick={onAdd}
        >
          Add
        </Button>
      ) : (
        ""
      )}
    </div>
  );
}
