import FinancialCompanyItems from "./financialCompanyItems";
import { Modal } from "antd";
import { useState, useEffect } from "react";

export default function FinancialCompanyForm({ configure, data, reRender }) {
  const [modalOpen, setModalOpen] = useState(false);
  useEffect(() => {
    setModalOpen(configure.modalOpen);
  });

  const callbackOnSave = (eventType) => {
    reRendering();
    modalControll(eventType);
  };

  const reRendering = () => {
    if (reRender) {
      const { render, setRender } = reRender;
      setRender(!render);
    }
  };

  const modalControll = (eventType) => {
    if (eventType !== "save" && configure.useModal) {
      setModalOpen(false);
    }
  };

  const financialCompanyItems = () => {
    return (
      <div>
        <FinancialCompanyItems data={data} callbackOnSave={callbackOnSave} />
      </div>
    );
  };

  return (
    <div>
      {configure.useModal ? (
        <Modal open={modalOpen}>financialCompanyItems()</Modal>
      ) : (
        financialCompanyItems()
      )}
    </div>
  );
}
