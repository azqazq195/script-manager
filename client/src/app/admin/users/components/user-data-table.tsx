import React, {useEffect, useState} from 'react';
import {User, userSample} from "@/data/user";
import {columns} from "@/app/admin/users/components/columns";
import {DataTable} from "@/components/data-table";
import {Input} from "@/components/ui/input";
import CreateUserDialog from "@/app/admin/users/components/create-user-dialog";

async function getUserData(filter: string): Promise<User[]> {
  if (!filter.trim()) {
    return userSample;
  }

  const lowerCaseFilter = filter.toLowerCase();

  return userSample.filter(user =>
    user.name.toLowerCase().includes(lowerCaseFilter) ||
    user.role.toLowerCase().includes(lowerCaseFilter) ||
    user.group.toLowerCase().includes(lowerCaseFilter)
  );
}

export default function UserDataTable() {
  const [filter, setFilter] = useState<string>("");
  const [data, setData] = useState<User[]>([]);

  useEffect(() => {
    const fetchFilteredData = () => {
      getUserData(filter)
        .then(filteredData => {
          setData(filteredData);
        })
        .catch(error => {
          console.error("데이터를 가져오는 중 오류가 발생했습니다:", error);
        });
    };

    fetchFilteredData();
  }, [filter]);

  const handleFilterChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setFilter(event.target.value);
  };

  return (
    <div>
      <div className="flex items-center py-4">
        <Input
          placeholder="검색"
          value={filter}
          onChange={handleFilterChange}
          className={"max-w-sm"}
          style={{boxShadow: '0 0 0'}}
        />
        <CreateUserDialog/>
      </div>
      <DataTable columns={columns} data={data}/>
    </div>
  )
};