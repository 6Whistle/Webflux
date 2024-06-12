import { NextResponse } from 'next/server';
import { SignJWT } from 'jose';

import { getJwtSecretKey } from "@/lib/server/auth";

import { UserData, UserDataPublic } from '@/types/UserData.type';
import { NextApiResponse } from 'next';

export interface I_ApiUserLoginRequest {
	email: string;
	password: string;
}

export interface I_ApiUserLoginResponse {
	success: boolean;
	userData?: UserData;
	message?: string;
}

export const dynamic = 'force-dynamic';
const jwtExpires = 60 * 60 * 24 * 7; // 7 days

// const userData: UserData = {
// 	id: 1,
// 	firstName: 'John',
// 	lastName: 'Doe',
// 	email: 'john@example.com',
// 	phone: '+1 234 567 890',
// 	password: '12345', // the kind of password an idiot would have on his luggage
// 	role: 'user',
// };

export async function POST(request: Request) {
	const body = (await request.json()) as I_ApiUserLoginRequest;

	// trim all values
	const { email, password } = Object.fromEntries(
		Object.entries(body).map(([key, value]) => [key, value.trim()]),
	) as I_ApiUserLoginRequest;

	if (!email || !password) {
		const res: I_ApiUserLoginResponse = {
			success: false,
			message: 'Either login or password is missing',
		};

		return NextResponse.json(res, { status: 400 });
	}

	return await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}/api/user/login`, 
		{
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify({ email, password }),
		}
	)
	.then(async (apiRes) => {
		return apiRes.ok ? 
		apiRes.json()
		.then((json) => {
			const response = NextResponse.json({ success: true, message: "SUCCESS" }, { status: 200 })
			response.cookies.set({
				name: 'userData',
				value: JSON.stringify(json.data),
				path: '/',
				expires: new Date(Date.now() + 1000 * 60 * 60 * 24),
			})
			response.cookies.set({
				name: 'accessToken',
				value: json.accessToken,
				path: '/',
				expires: new Date(Date.now() + 1000 * 60 * 60),
			})
			response.cookies.set({
				name: 'refreshToken',
				value: json.refreshToken,
				path: '/',
				expires: new Date(Date.now() + 1000 * 60 * 60 * 24),
			})
			return response
		})
		: NextResponse.json({ success: false, message: (await apiRes.json()).data }, { status: 401 })
	})
	.catch(async (error) => {
		return NextResponse.json({ success: false, message: JSON.stringify(error) }, { status: 400 })}
	)
}