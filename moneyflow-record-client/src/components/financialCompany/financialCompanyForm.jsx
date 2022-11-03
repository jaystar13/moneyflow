import FinancialCompanyItems from "./financialCompanyItems";
import { Modal } from "antd";
import { useState, useEffect } from "react";

export default function FinancialCompanyForm({ configure, data, reRender }) {
  const [modalOpen, setModalOpen] = useState(false);
  useEffect(() => {
    setModalOpen(configure.modalOpen);
  });

  return (
    <div>
      {configure.useModal ? (
        <Modal open={modalOpen}>
          <FinancialCompanyItems data={data} />
        </Modal>
      ) : (
        <FinancialCompanyItems data={data} />
      )}
    </div>
  );
}
