import { create } from "domain";

import { PrismaClient } from "@prisma/client";
const prisma = new PrismaClient();

export async function createUser() {
  await prisma.user.create({
    data: {
      email: "test email",
      username: "test username",
      provider: "test provider",
      provider_id: "test providerId",
    }
    })
    return Response.json('ok')
};

export async function getUser() {
  const user = await prisma.user.findMany();
  return user;
};