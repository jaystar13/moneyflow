import { createCodeType, updateCodeType } from "../../api/api";
import CodeTypeForm from "./codeTypeForm";
import { Modal, Form } from "antd";
import { useCallback } from "react";

export default function ModalCodeTypeForm({
  codeType,
  onUpdateCodeType,
  modalOpen,
  reRenderCodeTypeList,
}) {
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

  const onSubmit = useCallback((codeType) => {
    if (codeType.id === 0) {
      saveCodeType(codeType);
    } else {
      modifyCodeType(codeType.id, codeType);
    }
  }, []);

  return (
    <div>
      <Modal open={modalOpen} onCancel={handleCancel} footer={null}>
        <CodeTypeForm
          codeType={codeType}
          onSubmit={onSubmit}
          onUpdateCodeType={handleUpdateCodeType}
        />
      </Modal>
    </div>
  );
}
