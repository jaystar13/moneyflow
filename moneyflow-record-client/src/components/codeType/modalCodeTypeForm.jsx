import { createCodeType, updateCodeType } from "../../api/api";
import CodeTypeForm from "./codeTypeForm";
import { Button, Modal } from "antd";

export default function ModalCodeTypeForm({
  codeType,
  onUpdateCodeType,
  modalOpen,
  reRenderCodeTypeList,
}) {
  const handleSave = async () => {
    if (codeType.id === 0) {
      await saveCodeType(codeType);
    } else {
      await modifyCodeType(codeType.id, codeType);
    }
  };

  const saveCodeType = async (codeType) => {
    await createCodeType(codeType);
    reRenderCodeTypeList({ changedCodeType: codeType, isSaveAction: true });
  };

  const modifyCodeType = async (id, codeType) => {
    await updateCodeType(id, codeType);
    reRenderCodeTypeList({ changedCodeType: codeType, isSaveAction: false });
  };

  const handleCancel = () => {
    reRenderCodeTypeList({ isSaveAction: false });
  };

  const handleUpdateCodeType = (value, name) => {
    onUpdateCodeType(value, name);
  };

  return (
    <div>
      <Modal
        open={modalOpen}
        onOk={handleSave}
        onCancel={handleCancel}
        footer={[
          <Button key="submit" type="primary" onClick={handleSave}>
            Save
          </Button>,
          <Button key="back" onClick={handleCancel}>
            Cancle
          </Button>,
        ]}
      >
        <CodeTypeForm
          codeType={codeType}
          onUpdateCodeType={handleUpdateCodeType}
        />
      </Modal>
    </div>
  );
}
