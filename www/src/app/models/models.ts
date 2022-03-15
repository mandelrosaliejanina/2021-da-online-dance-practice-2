export interface User extends UserCredential {
  firstname: string,
  id: number,
  lastname: string,
  role: Role,
}

export enum Role {
  STUDENT = 'STUDENT',
  TEACHER = 'TEACHER'
}

export interface Level {
  id: string,
  description: string
}

export interface Course {
  id?: number,
  title: string,
  description: string,
  level: Level
}

export interface DFile {
  contentType: string,
  description: string,
  id: number,
  name: string,
  path: string,
}

export interface AccessToken {
  token: string,
  course: Course,
  activationDate?: Date,
  daysValid?: number,
  expireDate?: Date,
  isEditable?: boolean
}


export interface AccessTokenResponse {
  token: string,
  courseId: number,
  courseName: string,
  levelId: string,
  isValid: boolean
}

export interface Usage {
  course: Course,
  file: DFile
}

export interface UserCredential {
  username: string,
  password: string
}
