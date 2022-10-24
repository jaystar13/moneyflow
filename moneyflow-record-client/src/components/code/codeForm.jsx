import { Modal } from "antd";
import { useEffect, useState } from "react";
import CodeFormItems from "./codeFormItems";

export default function CodeForm({ configure, code, reRender }) {
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

  const callbackOnSubmit = (codeId) => {
    console.log("callbackOnSave");
    codeId === 0 ? setModalOpen(true) : setModalOpen(false);
    requestRendering();
  };

  return (
    <div>
      {configure.useModal ? (
        <Modal open={modalOpen} onCancel={handleOnCancel} footer={null}>
          <CodeFormItems code={code} callback={callbackOnSubmit} />
        </Modal>
      ) : (
        <CodeFormItems code={code} callback={callbackOnSubmit} />
      )}
    </div>
  );
}
