import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getAllCodeTypes, deleteCodeType } from "../api/api";

export default function CodeType() {
  const [codeTypes, setCodeTypes] = useState([]);

  const onLoadCodeTypes = async () => {
    const respose = await getAllCodeTypes();
    console.log(respose.data);
    setCodeTypes(respose.data);
  };

  const deleteCodeTypeAction = async (e) => {
    await deleteCodeType(e.target.id);
  };

  useEffect(() => {
    onLoadCodeTypes();
  }, []);

  return (
    <div>
      <Link to="add">
        <button>Add Code Type</button>
      </Link>
      <h1>Code Type List</h1>
      {codeTypes.map((codeType, index) => (
        <div key={index}>
          <h2>
            {codeType.id} : {codeType.name}
          </h2>
          <button type="submit">Modify</button>
          <button id={codeType.id} onClick={deleteCodeTypeAction}>
            Delete
          </button>
        </div>
      ))}
    </div>
  );
}
