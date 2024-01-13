"use client"

import {Card, CardContent, CardDescription, CardHeader, CardTitle} from "@/components/ui/card";
import {Separator} from "@/components/ui/separator";
import {Form, FormControl, FormField, FormItem, FormLabel, FormMessage} from "@/components/ui/form";
import {useForm} from "react-hook-form";
import {z} from "zod";
import {zodResolver} from "@hookform/resolvers/zod";
import {toast} from "@/components/ui/use-toast";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";
import {useRouter} from "next/navigation";

export default function Page() {
  const router = useRouter();
  const FormSchema = z.object({
    username: z.string().trim().min(2, {
      message: "이름은 2글자 이상 이어야 합니다.",
    }).max(4, {
      message: "이름은 4글자 미만 이어야 합니다."
    }),
    password: z.string().trim().min(4, {
      message: "비밀번호는 4자리 이상이어야 합니다."
    }),
  })

  const form = useForm<z.infer<typeof FormSchema>>({
    resolver: zodResolver(FormSchema),
    defaultValues: {
      username: "",
      password: "",
    },
  })

  function onSubmit(data: z.infer<typeof FormSchema>) {
    toast({
      title: "You submitted the following values:",
      description: (
        <pre className="mt-2 w-[340px] rounded-md bg-slate-950 p-4">
          <code className="text-white">{JSON.stringify(data, null, 2)}</code>
        </pre>
      ),
      duration: 1200
    })

    router.push('/main/profile')
  }

  return (
    <div className={"flex justify-center items-center min-h-screen"}>
      <Card className={"w-[500px] rounded-2xl shadow-lg p-6"}>
        <CardHeader className={"p-6"}>
          <CardTitle className={"pb-2"}>Script Manager</CardTitle>
          <CardDescription className={"pb-6"}>계정 생성은 관리자에게 요청해 주세요.</CardDescription>
          <Separator/>
        </CardHeader>
        <CardContent>
          <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="w-full space-y-6">
              <FormField
                control={form.control}
                name="username"
                render={({field}) => (
                  <FormItem>
                    <FormLabel className={"text-base"}>이름</FormLabel>
                    <FormControl>
                      <Input placeholder="이름" {...field} />
                    </FormControl>
                    <FormMessage/>
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="password"
                render={({field}) => (
                  <FormItem>
                    <FormLabel className={"text-base"}>비밀번호</FormLabel>
                    <FormControl>
                      <Input type="password" placeholder="비밀번호" {...field} />
                    </FormControl>
                    <FormMessage/>
                  </FormItem>
                )}
              />
              <div className="text-right">
                <Button type="submit">로그인</Button>
              </div>
            </form>
          </Form>
        </CardContent>
      </Card>
    </div>
  )
}