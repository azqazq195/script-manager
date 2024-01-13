"use client"

import {zodResolver} from "@hookform/resolvers/zod"
import {useForm} from "react-hook-form"
import * as z from "zod"
import {toast} from "@/components/ui/use-toast";
import {Form, FormControl, FormField, FormItem, FormLabel, FormMessage} from "@/components/ui/form";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";
import {User, userSample} from "@/data/user";
import React, {useEffect} from "react";

async function getUserData(): Promise<User> {
  return userSample[0]
}

const FormSchema =
  z.object({
    password: z.string().min(1, "비밀번호를 입력해 주세요."),
    confirm: z.string(),
  })
    .refine((data) => data.password === data.confirm, {
      message: "비밀번호가 다릅니다.",
      path: ["confirm"],
    })

export function AccountForm() {
  const [user, setUser] = React.useState<User>();
  const form = useForm<z.infer<typeof FormSchema>>({
    resolver: zodResolver(FormSchema),
    defaultValues: {
      password: "",
      confirm: ""
    },
  })

  useEffect(() => {
    getUserData().then(setUser)
  }, [user])

  function onSubmit(data: z.infer<typeof FormSchema>) {
    toast({
      title: "You submitted the following values:",
      description: (
        <pre className="mt-2 w-[340px] rounded-md bg-slate-950 p-4">
          <code className="text-white">{JSON.stringify(data, null, 2)}</code>
        </pre>
      ),
    })
  }

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
        <FormItem>
          <FormLabel>이름</FormLabel>
          <FormControl>
            <Input placeholder={user?.name} disabled/>
          </FormControl>
          <FormMessage/>
        </FormItem>
        <FormField
          control={form.control}
          name="password"
          render={({field}) => (
            <FormItem>
              <FormLabel>비밀번호</FormLabel>
              <FormControl>
                <Input placeholder="비밀번호" {...field} />
              </FormControl>
              <FormMessage/>
            </FormItem>
          )}
        />
        <FormField
          control={form.control}
          name="confirm"
          render={({field}) => (
            <FormItem>
              <FormLabel>비밀번호 확인</FormLabel>
              <FormControl>
                <Input placeholder="비밀번호 확인" {...field} />
              </FormControl>
              <FormMessage/>
            </FormItem>
          )}
        />
        <div className={"flex justify-end"}>
          <Button className={"ml-auto"} type="submit">변경</Button>
        </div>
      </form>
    </Form>
  )
}
