"use client";

import {Separator} from "@/components/ui/separator";
import UserDataTable from "@/app/admin/users/components/user-data-table";

export default function Page() {
  return (
    <div className="space-y-6">
      <div>
        <h3 className="text-lg font-medium">사용자</h3>
        <p className="text-sm text-muted-foreground">
          사용자 관리
        </p>
      </div>
      <Separator/>
      <div className="space-y-6">
        <UserDataTable/>
      </div>
    </div>
  )
}
