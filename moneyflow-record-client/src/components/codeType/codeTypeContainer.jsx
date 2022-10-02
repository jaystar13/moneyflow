import { useEffect, useState } from "react";
import { getAllCodeTypes, deleteCodeType, createCodeType } from "../../api/api";
import CodeTypeForm from "./codeTypeForm";
import CodeTypeList from "./codeTypeList";

export default function CodeType() {
  const [codeTypes, setCodeTypes] = useState([]);

  const [codeType, setCodeType] = useState({ id: 0, name: "" });

  const onCreate = async (codeType) => {
    await createCodeType(codeType);
    setCodeType({ id: 0, name: "" });
    onLoadCodeTypes();
  };

  const onRemove = (id) => {
    deleteCodeTypeAction(id);
    setCodeTypes(codeTypes.filter((codeType) => codeType.id !== id));
  };

  const deleteCodeTypeAction = async (id) => {
    await deleteCodeType(id);
    alert("삭제 성공");
  };

  const onModify = () => {
    console.log("onModify");
  };

  const onUpdateCodeType = (value, name) => {
    setCodeType({ ...codeType, [name]: value });
  };

  const onLoadCodeTypes = async () => {
    const respose = await getAllCodeTypes();
    setCodeTypes(respose.data);
  };

  useEffect(() => {
    onLoadCodeTypes();
  }, []);

  return (
    <div>
      <CodeTypeForm
        codeType={codeType}
        onUpdateCodeType={onUpdateCodeType}
        onCreate={onCreate}
      />
      <CodeTypeList
        codeTypes={codeTypes}
        onRemove={onRemove}
        onModify={onModify}
      />
    </div>
  );
}
