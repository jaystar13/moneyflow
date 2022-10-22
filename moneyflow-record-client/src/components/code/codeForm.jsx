import { Modal } from "antd";
import { useEffect, useState } from "react";
import CodeFormItems from "./codeFormItems";

export default function CodeFormModal({ configure, code, reRender }) {
  const [modalOpen, setModalOpen] = useState(false);

  useEffect(() => {
    setModalOpen(configure.modalOpen);
  }, [configure]);

  const handleOnCancel = () => {
    setModalOpen(false);
  };

  const requestRendering = () => {
    if (reRender) {
      const { render, setRender } = reRender;
      setRender(!render);
    }
  };

  const callbackOnSubmit = () => {
    console.log("callbackOnSave");
    requestRendering();
  };

  return (
    <div>
      {configure.useModal ? (
        <Modal open={modalOpen} onCancel={handleOnCancel} footer={null}>
          <CodeFormItems code={code} callbackOnSubmit={callbackOnSubmit} />
        </Modal>
      ) : (
        <CodeFormItems code={code} callbackOnSubmit={callbackOnSubmit} />
      )}
    </div>
  );
}
