import {Button} from "@/components/ui/button";
import {z} from "zod";
import {useForm} from "react-hook-form";
import {zodResolver} from "@hookform/resolvers/zod";
import {Form, FormControl, FormField, FormItem, FormLabel, FormMessage} from "@/components/ui/form";
import {Input} from "@/components/ui/input";
import {toast} from "@/components/ui/use-toast";
import {Dialog, DialogContent, DialogFooter, DialogHeader, DialogTitle, DialogTrigger} from "@/components/ui/dialog";
import React, {useEffect} from "react";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select";
import {Group, groupSample} from "@/data/group";
import {Role, roleSample} from "@/data/role";

const FormSchema = z.object({
  name: z.string().min(1, "이름이 빈칸일 수 없습니다."),
  role: z.string().min(1, "권한을 선택해 주세요."),
  group: z.string().min(1, "그룹을 선택해 주세요."),
})

async function getRoleData(): Promise<Role[]> {
  return roleSample
}

async function getGroupData(): Promise<Group[]> {
  return groupSample
}

export default function CreateUserDialog() {
  const [open, setOpen] = React.useState(false);
  const [roles, setRoles] = React.useState<Role[]>([]);
  const [groups, setGroups] = React.useState<Group[]>([]);

  const form = useForm<z.infer<typeof FormSchema>>({
    resolver: zodResolver(FormSchema),
    defaultValues: {
      name: "",
      role: "",
      group: "",
    },
  })

  useEffect(() => {
    if (!open) {
      form.reset()
    }

    getRoleData().then(setRoles);
    getGroupData().then(setGroups);
  }, [open, form, roles, groups])

  function onSubmit(data: z.infer<typeof FormSchema>) {
    toast({
      title: "You submitted the following values:",
      description: (
        <pre className="mt-2 w-[340px] rounded-md bg-slate-950 p-4">
          <code className="text-white">{JSON.stringify(data, null, 2)}</code>
        </pre>
      ),
    })

    setOpen(false)
  }

  return (
    <Dialog open={open} onOpenChange={setOpen}>
      <DialogTrigger asChild>
        <Button variant="outline" className={"ml-auto"}>
          사용자 추가
        </Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader className={"space-y-6"}>
          <DialogTitle>사용자 추가</DialogTitle>
          <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6">
              <FormField
                control={form.control}
                name="name"
                render={({field}) => (
                  <FormItem>
                    <FormLabel>이름</FormLabel>
                    <FormControl>
                      <Input placeholder="이름" {...field} />
                    </FormControl>
                    <FormMessage/>
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="role"
                render={({field}) => (
                  <FormItem>
                    <FormLabel>권한</FormLabel>
                    <Select onValueChange={field.onChange} defaultValue={field.value}>
                      <FormControl>
                        <SelectTrigger>
                          <SelectValue placeholder="권한 선택"/>
                        </SelectTrigger>
                      </FormControl>
                      <SelectContent>
                        {roles.map(role => (
                          <SelectItem key={role.id} value={role.name}>{role.name}</SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                    <FormMessage/>
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="group"
                render={({field}) => (
                  <FormItem>
                    <FormLabel>그룹</FormLabel>
                    <Select onValueChange={field.onChange} defaultValue={field.value}>
                      <FormControl>
                        <SelectTrigger>
                          <SelectValue placeholder="그룹 선택"/>
                        </SelectTrigger>
                      </FormControl>
                      <SelectContent>
                        {groups.map(group => (
                          <SelectItem key={group.id} value={group.name}>{group.name}</SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                    <FormMessage/>
                  </FormItem>
                )}
              />
              <DialogFooter>
                <Button variant={"outline"} type={"button"} onClick={() => setOpen(false)}>
                  취소
                </Button>
                <Button type={"submit"}>생성</Button>
              </DialogFooter>
            </form>
          </Form>
        </DialogHeader>
      </DialogContent>
    </Dialog>
  )
}